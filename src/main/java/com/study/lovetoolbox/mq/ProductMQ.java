package com.study.lovetoolbox.mq;

import com.study.lovetoolbox.constant.MQConstant;
import com.study.lovetoolbox.model.entity.MqLog;
import com.study.lovetoolbox.model.enums.NoticeTypeEnum;
import com.study.lovetoolbox.service.impl.MqLogService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProductMQ {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private MqLogService mqLogService;

    /**
     * send message
     *
     * @param message 消息
     */
    public void sendMessage(Integer type, String message) {
        if (ObjectUtils.isEmpty(type)) {
            return;
        }
        String key = "";
        if (type.equals(NoticeTypeEnum.MENU.getCode())) {
            key = MQConstant.ROUTING_KEY_MENU;
        } else if (type.equals(NoticeTypeEnum.APPLY.getCode())) {
            key = MQConstant.ROUTING_KEY_APPLY;
        }
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE, key, message);
        MqLog mqLog = new MqLog();
        mqLog.setQueue(key);
        mqLog.setMessage(message);
        mqLogService.save(mqLog);
    }
}
