package com.sqz.mycache.manager;

import com.sqz.mycache.config.CacheConfiguration;

public class Cache extends MemoryCache {

    private CacheConfiguration configure;

    private   CacheListener listener;

    public Cache(CacheConfiguration configure) {
        super(configure);
        this.configure = configure;
        if(!configure.getEternal()){
            listener = new CacheListener(this);
            listener.start();
        }
    }
    public CacheConfiguration getConfigure() {
        return configure;
    }

    // 销毁
    public void destory(){
        try{
            super.clear();
            if(listener != null){
                listener.interrupt();
                listener.stop();
                listener = null;
            }
        }catch (Exception e) {
        }

    }

}
