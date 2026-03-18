package com.example.activmq01.amq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AMQSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = "test-queue")
    public void receiveMessage(String message) {
        // This method will be triggered when a message is received from the specified destination
        System.out.println("Received message: " + message);
    }
}
