package com.study.lovetoolbox.mq;

import com.rabbitmq.client.Channel;
import com.study.lovetoolbox.constant.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class CustomerMQ {

    @RabbitListener(queues = {MQConstant.QUEUE}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliverTag) {
        // todo 死信队列
        try {
            if (StringUtils.isEmpty(message)) {
                channel.basicNack(deliverTag, false, false);
                return;
            }

            channel.basicAck(deliverTag, false);
        } catch (IOException e) {
            log.error("channel错误, {}", e.getMessage());
        }
    }
}
