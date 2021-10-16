package com.sqz.springcache.Controller;

import com.sqz.springcache.entry.User;
import com.sqz.springcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping("/user/add")
    public String add(String name) {
        userService.sava(name);
        return "sucess";
    }

    @ResponseBody
    @RequestMapping("/user/del/{id}")
    public String del(@PathVariable("id") Integer id) {
        userService.del(id);
        return "sucess";
    }

}
