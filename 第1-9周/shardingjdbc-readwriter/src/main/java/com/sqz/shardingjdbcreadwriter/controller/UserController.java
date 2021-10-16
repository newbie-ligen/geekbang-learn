package com.sqz.shardingjdbcreadwriter.controller;

import com.sqz.shardingjdbcreadwriter.entry.UserEntity;
import com.sqz.shardingjdbcreadwriter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/add")
    public  String insert(String name) {
        userService.insertUser(name);
        return "success";
    }

    @RequestMapping("/user/list")
    public List<UserEntity> list() {
        return userService.findUser();
    }

}
