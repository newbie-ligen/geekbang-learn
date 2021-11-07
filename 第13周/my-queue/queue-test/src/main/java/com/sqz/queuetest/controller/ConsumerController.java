package com.sqz.queuetest.controller;

import com.sqz.queuetest.entry.Message;
import com.sqz.queuetest.entry.MessageHodler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsumerController {

    @Autowired
    private MessageHodler messageHodler;

    @RequestMapping("/product")
    public Message product(String topic, String consumer) {
        return messageHodler.get(topic,consumer);
    }
}
