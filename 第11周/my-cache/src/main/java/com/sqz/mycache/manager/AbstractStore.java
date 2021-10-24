package com.sqz.mycache.manager;

import com.sqz.mycache.entry.Element;

import java.util.Arrays;
import java.util.Map;

public abstract class AbstractStore implements  Store{

    protected Map<Object, Element> map;

    public AbstractStore(){}

    public AbstractStore(Map<Object, Element> map){
        this.map = map;
    }

    @Override
    public Element get(Object key) {
        return map.get(key);
    }

    public Map<Object, Element> getAll(){
        return map;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Element put(Element e) {
        return map.put(e.getKey(), e);
    }

    @Override
    public void remove(Object key) {
        map.remove(key);
    }

    @Override
    public Integer size() {
        return map.size();
    }

    @Override
    public void removeAll(Object[] keys) {
        Arrays.stream(keys).forEach(key->remove(key));
    }
}
