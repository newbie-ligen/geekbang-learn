package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import io.kimmking.rpcfx.demo.provider.anno.DubboService;

@DubboService(version = "0.0.2",group = "g2")
public class UserServiceV2Impl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "sunqingzhong" + System.currentTimeMillis());
    }
}
