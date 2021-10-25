package com.sqz.activemqdemo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerQueueService {

    @JmsListener(destination = "ActiveMQQueue",containerFactory="queueListenerFactory")
    public void handleMessage(String name) {
        System.out.println("成功接受queue:" + name);
    }

}
