package com.vegetablesale.securityjwt.service;

import com.vegetablesale.securityjwt.entity.ResponseModel;
import com.vegetablesale.securityjwt.pojo.LoginDTO;

public interface LoginService {

    public ResponseModel checkLogin(LoginDTO login);
}
