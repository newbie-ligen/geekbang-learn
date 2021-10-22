package com.sqz.pubsub.send;

import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 **/
@Component
public class SendMsg {

    @Autowired
    RedisTemplate redisTemplate;

    public void sendMessage(String topic, String message) {
        //这里给订阅该主题的链接的每个队列进行广播该消息
        redisTemplate.getConnectionFactory().getConnection().publish(topic.getBytes(CharsetUtil.UTF_8), message.getBytes());

    }


}
