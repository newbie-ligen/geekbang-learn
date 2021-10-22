package com.sqz.lock.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.UUID;

/**
 *
 **/
@Component
public class LockUtils {


    private int internalLockLeaseTime = 30;//锁过期时间
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;


    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取一个redis分布锁
     * @param lockKey        锁住的key
     * @return
     */
    public boolean lock(String lockKey) {
        Jedis jedis = jedisPool.getResource();
        SetParams setParams = new SetParams();
        setParams.ex(internalLockLeaseTime);
        setParams.nx();
        String requestId = UUID.randomUUID().toString().replace("-", "");
        ThreadLocalUtils.put(requestId);
        String result = jedis.set(lockKey, requestId, setParams);
        if(LOCK_SUCCESS.equals(result)) {
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
        Jedis jedis = jedisPool.getResource();
        String requestId = ThreadLocalUtils.get();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;


    }



}
