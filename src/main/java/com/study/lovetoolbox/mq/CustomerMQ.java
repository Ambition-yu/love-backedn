package com.study.lovetoolbox.mq;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import com.study.lovetoolbox.constant.CommonConstant;
import com.study.lovetoolbox.constant.MQConstant;
import com.study.lovetoolbox.model.entity.Menu;
import com.study.lovetoolbox.model.entity.UserNotice;
import com.study.lovetoolbox.model.enums.MealTimeEnum;
import com.study.lovetoolbox.service.MenuService;
import com.study.lovetoolbox.service.UserNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;


@Component
@Slf4j
public class CustomerMQ {

    @Resource
    private UserNoticeService userNoticeService;

    @Resource
    private MenuService menuService;

    @RabbitListener(queues = {MQConstant.QUEUE_MENU}, ackMode = "MANUAL")
    public void receiveMenuMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliverTag) {
        // todo 死信队列
        try {
            if (StringUtils.isEmpty(message)) {
                channel.basicNack(deliverTag, false, false);
                return;
            }
            String[] split = message.split(",");
            if (split.length < 4) {
                channel.basicNack(deliverTag, false, false);
                return;
            }
            Menu menu = menuService.getById(split[0]);
            if (ObjectUtils.isEmpty(menu)) {
                channel.basicNack(deliverTag, false, false);
                return;
            }
            UserNotice notice = new UserNotice();
            notice.setUserId(Long.valueOf(split[2]));
            notice.setCreateUser(Long.valueOf(split[1]));
            notice.setContent(DateUtil.format(menu.getMenuDate(), "yyyy年MM月dd日") + "【" + MealTimeEnum.getValue(menu.getMealTime()) + "】菜单已"+split[3]+"，请查看");
            userNoticeService.save(notice);
            channel.basicAck(deliverTag, false);
        } catch (IOException e) {
            log.error("channel错误, {}", e.getMessage());
        }
    }

    @RabbitListener(queues = {MQConstant.QUEUE_MENU}, ackMode = "MANUAL")
    public void receiveApplyMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliverTag) {
        // todo 死信队列
        try {
            if (StringUtils.isEmpty(message)) {
                channel.basicNack(deliverTag, false, false);
                return;
            }
            String[] split = message.split(",");
            if (split.length < 4) {
                channel.basicNack(deliverTag, false, false);
                return;
            }
            Menu menu = menuService.getById(split[0]);
            if (ObjectUtils.isEmpty(menu)) {
                channel.basicNack(deliverTag, false, false);
                return;
            }
            UserNotice notice = new UserNotice();
            notice.setUserId(Long.valueOf(split[1]));
            notice.setCreateUser(Long.valueOf(split[0]));
            if (split[3].equals(CommonConstant.BIND)) {
                notice.setContent("【申请消息】用户【"+split[2]+"】申请与您绑定关系");
            } else if (split[3].equals(CommonConstant.UNBIND)) {
                notice.setContent("【解绑消息】用户【"+split[2]+"】已与您解绑关系");
            }
            userNoticeService.save(notice);
            channel.basicAck(deliverTag, false);
        } catch (IOException e) {
            log.error("channel错误, {}", e.getMessage());
        }
    }
}
