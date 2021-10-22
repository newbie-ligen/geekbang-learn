package com.sqz.lock.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 **/
@Component
public class LockUtils {

    private int internalLockLeaseTime = 3;//锁过期时间

    private static final long RELEASE_SUCCESS = 1L;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取一个redis分布锁
     * @param lockKey        锁住的key
     * @return
     */
    public boolean lock(String lockKey) {
        String requestId = UUID.randomUUID().toString().replace("-", "");
        ThreadLocalUtils.put(requestId);
        Boolean res = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, internalLockLeaseTime, TimeUnit.SECONDS);
        if(res) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取一个redis分布锁
     * @param lockKey        锁住的key
     * @return
     */
    public boolean unLock(String lockKey) {
        String requestId = ThreadLocalUtils.get();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = redisTemplate.getConnectionFactory().getConnection().eval(script.getBytes(), ReturnType.INTEGER, 1, lockKey.getBytes(), requestId.getBytes());
        if (RELEASE_SUCCESS==result) {
            return true;
        }
        return false;
    }

}
