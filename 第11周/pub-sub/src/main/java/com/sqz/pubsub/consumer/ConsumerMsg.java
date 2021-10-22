package com.sqz.pubsub.consumer;
import com.google.gson.Gson;
import com.sqz.pubsub.pojo.OrderInfo;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 *
 **/
@Component
public class ConsumerMsg implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(message.getChannel());
        String content = new String(message.getBody());
        System.out.println("接收到topic->"+topic+"消息内容->"+content);
        Gson gson = new Gson();
        OrderInfo orderInfo = gson.fromJson(content, OrderInfo.class);
        System.out.println("订单业务操作");
    }
}
