package com.vegetablesale.securityjwt.service.impl;

import com.vegetablesale.securityjwt.dao.UserMapper;
import com.vegetablesale.securityjwt.pojo.MyUser;
import com.vegetablesale.securityjwt.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(MyUser myUser) {
        userMapper.register(myUser);
    }

    @Override
    public MyUser findUserByName(String username) {
        MyUser myUser = userMapper.findUserByName(username);
        return myUser;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        System.out.println("newPassword = " + newPassword);
        MyUser userUP = (MyUser) user;
        System.out.println("userUP = " + userUP);
        userUP.setPassword(newPassword);
        userMapper.updatePW(userUP);
        System.out.println("user = " + user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("准备开始查询");
        MyUser myUser = userMapper.findUserByName(username);
        if (Objects.isNull(myUser)){
            System.out.println("用户不存在");
            throw new UsernameNotFoundException(username);
        }
        System.out.println("查询用户："+myUser);

        return myUser;
    }
}
