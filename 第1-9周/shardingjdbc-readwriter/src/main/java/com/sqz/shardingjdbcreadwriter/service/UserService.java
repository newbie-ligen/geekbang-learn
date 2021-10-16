package com.sqz.shardingjdbcreadwriter.service;

import com.sqz.shardingjdbcreadwriter.entry.UserEntity;
import com.sqz.shardingjdbcreadwriter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<UserEntity> findUser() {
        return  userMapper.findUser();
    }

    public void insertUser(String userName){
          userMapper.insertUser(userName);
    }

}
