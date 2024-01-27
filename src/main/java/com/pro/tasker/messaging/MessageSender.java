package com.pro.tasker.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String exchange, String routingKey, Object message) {
        if (message instanceof String) {
            amqpTemplate.convertAndSend(exchange, routingKey, message);
            System.out.println("Message sent: " + message);
        } else {
            throw new IllegalArgumentException("Unsupported message type: " + message.getClass());
        }
    }
}