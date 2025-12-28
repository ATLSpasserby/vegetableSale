package com.vegetablesale.securityjwt.controller;

import com.vegetablesale.securityjwt.entity.ResponseModel;
import com.vegetablesale.securityjwt.pojo.LoginDTO;
import com.vegetablesale.securityjwt.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseModel login(@RequestBody LoginDTO login){
        return loginService.checkLogin(login);
    }
}
