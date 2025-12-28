package com.vegetablesale.securityjwt.service;

import com.vegetablesale.securityjwt.pojo.MyUser;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;

public interface UserService extends UserDetailsService, UserDetailsPasswordService {
    void register(MyUser myUser);

    MyUser findUserByName(String username);
}
