package com.sqz.pubsub.controller;

import com.google.gson.Gson;
import com.sqz.pubsub.constrants.RedisConstrants;
import com.sqz.pubsub.pojo.OrderInfo;
import com.sqz.pubsub.send.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 **/
@RestController
public class TestController {
    @Autowired
    private SendMsg sendMsg;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/send")
    public String send(@RequestBody OrderInfo orderInfo) {
        //减库存
        String script = "local stock= redis.call('HINCRBY',KEYS[1],ARGV[1],ARGV[3]); if stock<0 then redis.call('HINCRBY',KEYS[1],ARGV[1],ARGV[2]); return -1 else return stock end;";
        Long result = redisTemplate.getConnectionFactory().getConnection().eval(script.getBytes(),ReturnType.INTEGER,1,
                RedisConstrants.ORDER_NUM_HASH_KEY.getBytes(),
                orderInfo.getGoodsId().getBytes(),
                String.valueOf(orderInfo.getGoodsNum()).getBytes(),
                String.valueOf(-orderInfo.getGoodsNum()).getBytes());
        if(result>=0) {
            Gson gson = new Gson();
            sendMsg.sendMessage(RedisConstrants.ORDER_TOPIC, gson.toJson(orderInfo));
            return "下单成功";
        }
        return "库存不足";
    }

    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        // 由高位到低位
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

}
