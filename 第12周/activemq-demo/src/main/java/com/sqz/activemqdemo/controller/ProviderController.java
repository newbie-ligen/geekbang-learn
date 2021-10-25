package com.sqz.activemqdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.management.resources.agent;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 **/
@RestController
public class ProviderController {
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send/queue")
    public String  sendQueue(String msg) {
        jmsMessagingTemplate.convertAndSend(queue, msg);
        return "success";
    }

    @RequestMapping("send/topic")
    public String sendTopic(String msg) {
        jmsMessagingTemplate.convertAndSend(topic, msg);
        return "success";
    }


}
