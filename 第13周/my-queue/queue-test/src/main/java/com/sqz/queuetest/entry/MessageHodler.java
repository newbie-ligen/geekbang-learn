package com.sqz.queuetest.entry;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MessageHodler {


    private Map<String,Integer> offsetMap = new HashMap<>();

    private Map<String,Message[]> messages = new HashMap<>();

    private Map<String,Integer> tailMap = new HashMap<>();



    public void add(String topic,Message message) {
        Message[] messages = this.messages.get(topic);
        if(messages==null) {
            messages = new Message[1024];
        }
        tailMap.computeIfAbsent(topic,k->0);
        messages[tailMap.get(topic)] = message;
        tailMap.put(topic,tailMap.get(topic)+1);
    }

    public Message get(String topic, String consumerName) {
        Integer index = offsetMap.get(consumerName);
        if(index==null) {
            index = 0;
        }
        offsetMap.put(consumerName,++index);
        Message[] messages = this.messages.get(topic);
        return messages[index];
    }

}
