package com.vegetablesale.securityjwt.dao;

import com.vegetablesale.securityjwt.pojo.MyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void register(MyUser myUser);

    MyUser findUserByName(String username);

    void updatePW(MyUser userUP);
}
