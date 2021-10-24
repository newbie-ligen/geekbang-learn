package com.sqz.mycache.entry;

import java.io.Serializable;
/**
 * 缓存元素，所对应的实体
 */
public class Element implements Serializable {
    //缓存key
    private Object key;
    //缓存值
    private Object value;

    //使用次数
    private volatile long useCount=0;
    //过期时间  0不过期
    private volatile long expire=0;
    //最近一次使用时间
    private volatile long lastAccessTime;
    //存活时间
    private volatile int timeToIdle = 0;
    // 最后更新时间
    private volatile long lastUpdateTime;
    // 创建时间
    private transient long creationTime;
    //每次access是否刷新过期时间 0不刷新 1刷新
    private volatile  int refreshExpireTime = 0;

    public Element( Object key,  Object value){
        this.key = key;
        this.value = value;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessTime = System.currentTimeMillis();
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getUseCount() {
        return useCount;
    }

    public void setUseCount(long useCount) {
        this.useCount = useCount;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public int getTimeToIdle() {
        return timeToIdle;
    }

    public void setTimeToIdle(int timeToIdle) {
        this.timeToIdle = timeToIdle;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getRefreshExpireTime() {
        return refreshExpireTime;
    }

    public void setRefreshExpireTime(int refreshExpireTime) {
        this.refreshExpireTime = refreshExpireTime;
    }

    public boolean isRefreshExpireTime() {
        return refreshExpireTime==1;
    }

    /**
     * 增加使用次数
     */
    public void addUseCount(){
        useCount ++;
    }

    /**
     * 刷新最后一次使用时间
      */
    public void refreshLastAccessTime(){
        lastAccessTime = System.currentTimeMillis();
    }

    /**
     * 判断是否过期
     * @return
     */
    public boolean isExpired() {
        if (isEternal()) {
            return false;
        }
        return System.currentTimeMillis()>expire;
    }

    public void setExpire() {
        this.expire = getExpirationTime();
    }

    /**
     *获取过期时间
     */
    public long getExpirationTime() {
        if (isEternal()) {
            return Long.MAX_VALUE;
        }
        long expirationTime = 0;
        if(isRefreshExpireTime()) {
            long mostRecentTime = Math.max(creationTime, lastAccessTime);
            expirationTime = mostRecentTime + getTimeToIdle() * 1000;
        } else {
            expirationTime = creationTime + getTimeToIdle() * 1000;
        }
        return expirationTime;
    }

    // 是否是不会过期
    public boolean isEternal() {
        return (0 == expire);
    }
}
