package com.vegetablesale.securityjwt.dao;

import com.vegetablesale.securityjwt.pojo.MyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyMapper {
    MyUser queryByUsername(String username);
}
