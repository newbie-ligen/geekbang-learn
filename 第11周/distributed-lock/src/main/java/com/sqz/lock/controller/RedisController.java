package com.sqz.lock.controller;

import com.sqz.lock.utils.LockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 查询redis
 **/
@RestController
public class RedisController {

    @Autowired
    private LockUtils lockUtils;

    @RequestMapping("/lock")
    public String getLock(String key) {

        try {
            while (!lockUtils.lock(key)) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            System.out.println("开始执行业务逻辑:" + key);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("结束执行业务逻辑:" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lockUtils.unLock(key);
        }

        return "success";
    }


}
