package com.sqz.springmultidatasource.service;

import com.sqz.springmultidatasource.entry.UserEntity;
import com.sqz.springmultidatasource.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
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
