package com.study.lovetoolbox.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.study.lovetoolbox.constant.MQConstant;

public class MQInit {

    public static void main(String[] args) {
        // 初始化mq信息
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(MQConstant.EXCHANGE, "direct");

            // 创建队列
            channel.queueDeclare(MQConstant.QUEUE_MENU, true, false, false, null);
            // 队列绑定到交换机上
            channel.queueBind(MQConstant.QUEUE_MENU, MQConstant.EXCHANGE, MQConstant.ROUTING_KEY_MENU);

            // 创建队列
            channel.queueDeclare(MQConstant.QUEUE_APPLY, true, false, false, null);
            // 队列绑定到交换机上
            channel.queueBind(MQConstant.QUEUE_APPLY, MQConstant.EXCHANGE, MQConstant.ROUTING_KEY_APPLY);
        } catch (Exception e) {

        }
    }
}
