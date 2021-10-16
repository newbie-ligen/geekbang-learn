package com.sqz.mybatiscache.service;

import com.sqz.mybatiscache.entry.User;
import com.sqz.mybatiscache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


}
