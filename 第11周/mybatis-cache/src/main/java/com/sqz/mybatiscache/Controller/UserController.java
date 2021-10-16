package com.sqz.mybatiscache.Controller;

import com.sqz.mybatiscache.entry.User;
import com.sqz.mybatiscache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/user/{id}")
    public User get(@PathVariable("id") Integer id) {
        User user1 = userService.getUserById(id);
        return user1;
    }


}
