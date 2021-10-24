package com.sqz.redissiondemo.service;

import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    public void bucket() {
        RBucket<Object> bucket = redissonClient.getBucket("myBuket");

        Integer value = new Integer(1);

        bucket.set(value);

        boolean trySet = bucket.trySet(new Integer(3));
        System.out.println(trySet);

        boolean b = bucket.compareAndSet(new Integer(1), new Integer(5));
        System.out.println(b);

        Object andSet = bucket.getAndSet(new Integer(6));
        System.out.println(andSet);

        Object o = bucket.get();
        System.out.println(o);
    }

    public void bitset() {
        RBitSet set  = redissonClient.getBitSet("myRBitSet");
        set.set(0, true);
        set.set(1, false);
        boolean b = set.get(1);
        System.out.println(b);
        RBitSet anotherBitset  = redissonClient.getBitSet("anotherBitset");
        anotherBitset.set(0, true);
        anotherBitset.set(1, false);
        set.and("anotherBitset");
        System.out.println(set.get(0)+","+set.get(1));
        set.xor("anotherBitset");
        System.out.println(set.get(0)+","+set.get(1));
    }

    public void atomicLong () {
        RAtomicLong atomicLong = redissonClient.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
        atomicLong.incrementAndGet();
        System.out.println(atomicLong.get());
        atomicLong.compareAndSet(4,8);
        System.out.println(atomicLong.get());

    }

    public void pubsub () {
        RTopic mytopic = redissonClient.getTopic("myTopic");
        int listenerId = mytopic.addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence charSequence, String message) {
                System.out.println(message);
            }
        });
        RTopic topic = redissonClient.getTopic("myTopic");
        long clientsReceivedMessage = topic.publish("hello");

    }


    public void bloom () {
        RBloomFilter<Integer> bloomFilter = redissonClient.getBloomFilter("bloomFilter");
        bloomFilter.tryInit(55000000L, 0.03);

        bloomFilter.add(new Integer(1));
        bloomFilter.add(new Integer(2));

        boolean contains = bloomFilter.contains(new Integer(2));
        System.out.println(contains);
        long count = bloomFilter.count();
        System.out.println(count);
    }

    public void longAdder () {
        RLongAdder atomicLong = redissonClient.getLongAdder("myLongAdder");
        atomicLong.add(12);
        atomicLong.increment();
        System.out.println(atomicLong.sum());
        atomicLong.decrement();
        System.out.println(atomicLong.sum());
        long sum = atomicLong.sum();
        System.out.println(sum);

    }


    public void hyperLogLog () {
        RHyperLogLog<Integer> log = redissonClient.getHyperLogLog("log");
        log.add(1);
        log.add(2);
        log.add(3);
        log.add(3);
        long count = log.count();
        System.out.println(count);

    }

    public void rateLimiter () {
        RRateLimiter limiter = redissonClient.getRateLimiter("myLimiter");
        //  设置速率，10秒中产生5个令牌
        limiter.trySetRate(RateType.OVERALL, 5, 10, RateIntervalUnit.SECONDS);
        limiter.acquire(3);
        System.out.println("拿到3个令牌");
        limiter.acquire(2);
        System.out.println("拿到2个令牌");
        limiter.acquire(2);
        System.out.println("拿到2个令牌");
        //试图获取一个令牌，获取到返回true
        limiter.tryAcquire(1);
    }

    public void idGenerator () {
        redissonClient.getIdGenerator("simpleBitset");
        RIdGenerator generator = redissonClient.getIdGenerator("generator");
// Initialize with start value = 12 and allocation size = 20000

        generator.tryInit(12, 20000);
        long id = generator.nextId();
        System.out.println(id);
    }

}
