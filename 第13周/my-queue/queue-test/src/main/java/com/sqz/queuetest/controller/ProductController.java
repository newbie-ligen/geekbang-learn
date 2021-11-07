package com.sqz.queuetest.controller;

import com.sqz.queuetest.entry.Message;
import com.sqz.queuetest.entry.MessageHodler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private MessageHodler messageHodler;

    @RequestMapping("/product")
    public String product(String topic,String message) {
        messageHodler.add(topic,new Message(topic,message));
        return "success";
    }

}
