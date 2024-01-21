package com.pro.tasker.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RabbitMQController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send")
    public String sendMessage() {
        messageSender.sendMessage("your-exchange-name", "your-routing-key", "Hello, RabbitMQ!");
        return "Message sent!";
    }
}