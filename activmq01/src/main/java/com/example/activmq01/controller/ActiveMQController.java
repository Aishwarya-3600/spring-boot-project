package com.example.activmq01.controller;

import com.example.activmq01.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activemq")
public class ActiveMQController {

    @Autowired
    private JmsTemplate jmsTemplate;

    // Send a message to a queue
    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest request) {
        jmsTemplate.convertAndSend(request.destination, request.message);
        return "Message sent to queue: " + request.destination;
    }

    // Receive messages from any queue (example: test-queue)
    @JmsListener(destination = "test-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message from test-queue: " + message);
    }
}
