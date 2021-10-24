package com.sqz.mycache.manager;

import com.sqz.mycache.entry.Element;

import java.util.Collection;

interface Store {
    // 获得缓存名字
    String getName();

    // 存放元素
    Element put(Element e);

    Collection<Element> putAll(Collection<Element> elements);

    // 获取元素
    Element get(Object key);

    // 清除元素
    void clear();

    // 移除元素
    void remove(Object key);

    void removeAll(Object[] keys);

    // 获得的元素长度
    Integer size();
}

