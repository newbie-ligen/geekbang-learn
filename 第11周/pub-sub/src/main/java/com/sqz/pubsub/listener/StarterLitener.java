package com.sqz.pubsub.listener;

import com.sqz.pubsub.constrants.RedisConstrants;
import com.sqz.pubsub.consumer.ConsumerMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * */
@Component
public class StarterLitener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ConsumerMsg consumer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //初始化库存
        redisTemplate.opsForHash().put(RedisConstrants.ORDER_NUM_HASH_KEY,"s0001", 10);
        redisTemplate.opsForHash().put(RedisConstrants.ORDER_NUM_HASH_KEY,"s0002", 10);
        redisTemplate.opsForHash().put(RedisConstrants.ORDER_NUM_HASH_KEY,"s0003", 10);
        //绑定消费者
        redisTemplate.getConnectionFactory().getConnection().subscribe(consumer, RedisConstrants.ORDER_TOPIC.getBytes());
    }
}
