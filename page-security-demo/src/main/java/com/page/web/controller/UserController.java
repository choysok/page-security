package com.page.web.controller;

import com.page.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query(){
       // System.out.println(ReflectionToStringBuilder.toString("", ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping(value = "/user/{id}")

    public User getInfo(@PathVariable String id){
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
