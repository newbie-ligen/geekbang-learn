package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import io.kimmking.rpcfx.demo.provider.anno.DubboService;

@DubboService(version = "0.0.1",group = "g1")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
