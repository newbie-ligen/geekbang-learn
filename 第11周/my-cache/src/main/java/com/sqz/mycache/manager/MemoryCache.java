package com.sqz.mycache.manager;

import com.sqz.mycache.config.CacheConfiguration;
import com.sqz.mycache.entry.Element;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryCache extends AbstractStore implements  Store{

    private final CacheConfiguration configure;
    private final static Map<Object, Element> map = new HashMap<Object, Element>();
    private final CheckManager checkManager;


    // 暂时仅弄一个构造
    public MemoryCache(CacheConfiguration configure){
        super(map);
        this.configure = configure;
        this.checkManager = new CheckManager(configure, map);
    }


    @Override
    public String getName() {
        return configure.getCacheName();

    }

    @Override
    public Collection<Element> putAll(Collection<Element> elements) {
        if(elements == null){
            throw new NullPointerException("elements can't be null");
        }
        check(elements.size());
        for(Element e : elements){
            putElementStatus(e);
            super.put(e);
        }
        return elements;
    }

    // 检查元素是否超过了
    public void check(int checkSize){
        if(checkSize <= 0){
            return;
        }
        Object[] keys = checkManager.checkConfigure(checkSize);
        if(keys !=null) {
            removeAll(keys);
        }
    }

    // 如果eternal 为true，表示元素永不过期,默认忽略最小元素控制
    public void putElementStatus(Element e){
        checkElement( e);
        if(!configure.getEternal()){
            e.setTimeToIdle(configure.getTimeToIdleSeconds());
            e.setExpire();
        }else{
            e.setTimeToIdle(0);
            e.setExpire(0);
        }
    }

    // 使用一次之后刷新使用过期时间，以及使用次数
    // 并检查该元素是否过期
    public void changeElement(Element e){
        e.addUseCount();
        if(!configure.getEternal()){
            e.refreshLastAccessTime();
        }
    }

    @Override
    public  Element get(Object key) {
        Element e = super.get(key);
        if(e != null){
            if(!e.isExpired()){
                changeElement(e);
            }else{
                synchronized (this) {
                    remove(e.getKey());
                    e = null;
                }
            }
        }
        return e;
    }

    // 检查元素 是否为空
    public boolean checkElement(Element e){
        if(e == null){
            throw new NullPointerException("Element can't be  null ");
        }
        if(e.getKey() == null){
            throw new NullPointerException("Element key can't be  null ");
        }
        return true;
    }

    @Override
    public synchronized void removeAll(Object[] keys) {
        super.removeAll(keys);
    }



}
