package com.sqz.queuetest.entry;

public class Message {

    private String topic;

    private Object body;

    public Message(String topic, Object body) {
        this.topic = topic;
        this.body = body;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
