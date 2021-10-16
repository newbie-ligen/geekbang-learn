package com.sqz.springcache.service;

import com.sqz.springcache.entry.User;
import com.sqz.springcache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="users")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable( key = "'user-' + #id")
    public User getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    public User sava(String name) {
        User user = new User();
        user.setUsername(name);
        userMapper.insertSelective(user);
        System.out.println(user.getId());
        return user;
    }


    @CacheEvict( key = "'user-' + #id")
    public void del(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
