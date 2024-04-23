package com.study.lovetoolbox.mq;

import com.study.lovetoolbox.constant.MQConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProductMQ {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE, MQConstant.ROUTING_KEY, message);
    }
}
