package com.sqz.activemqdemo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 **/
@Component
public class ConsumerTopicService {
    @JmsListener(destination = "ActiveMQTopic", containerFactory="topicListenerFactory")
    public void handleMessage(String name) {
        System.out.println("成功接受topic:" + name);
    }
}
