package com.pro.tasker.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @RabbitListener(queues = "your-queue-name")
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
        // todo implement logic to handle receiver message :)
    }
}