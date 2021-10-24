package com.sqz.mycache.enums;

public enum EvictionType {

    // FIFO ：先进先出
    // LFU:最少使用,一直以来最少被使用的，缓存缓存有一个hit属性，清除hit最小的
    // LRU:最近最少使用，缓存元素有个时间戳，当缓存容量满了，而又需要腾出新地方
    FIFO,
    LFU,
    LRU;

}
