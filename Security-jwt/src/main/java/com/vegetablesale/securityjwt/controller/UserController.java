package com.vegetablesale.securityjwt.controller;

import com.vegetablesale.securityjwt.pojo.MyUser;
import com.vegetablesale.securityjwt.entity.Result;
import com.vegetablesale.securityjwt.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result registerByUP(@RequestBody MyUser myUser){

        System.out.println("开始注册程序");

        if (Objects.isNull(myUser)){
            return Result.error("用户信息为空");
        }
        MyUser userIN = userService.findUserByName(myUser.getUsername());
        if (Objects.nonNull(userIN)){
            System.out.println("用户已存在1");
            System.out.println("user = " + userIN);
            return Result.error("用户名已存在");
        }
        if (userIN != null){
            System.out.println("用户已存在2");
            System.out.println("user = " + userIN);
            return Result.error("用户名已存在");
        }
        System.out.println("user = " + userIN);
        userService.register(myUser);
        return Result.success();
    }

}
