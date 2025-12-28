package com.vegetablesale.securityjwt.service.impl;

import com.vegetablesale.securityjwt.entity.ResponseModel;
import com.vegetablesale.securityjwt.pojo.LoginDTO;
import com.vegetablesale.securityjwt.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    //登录认证
    @Override
    public ResponseModel checkLogin(LoginDTO login) {
        //获取用户名密码
        String username = login.getUsername();
        String password = login.getPassword();
        //将用户名密码放入未认证的authentication对象
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username
                ,password);

        //发起Authentication认证失败的话会终止程序运行无法返回认证失败信息，可以使用trycatch捕获异常
        try {
            //发起认证并得到认证后的authentication对象
            Authentication authentication = authenticationManager.authenticate(authRequest);

            if (authentication.isAuthenticated()){
                //返回认证成功信息（JSON），并应该注意将认证信息authentication放入安全上下文中方便后续过滤器调用（重要）
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseModel.authSuccess("认证成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //返回认证失败信息
        return ResponseModel.authFailed("认证失败");
    }
}
