package com.vegetablesale.securityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.vegetablesale.securityjwt.entity.ResponseModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置响应格式为json，并且设置为中文防止乱码
        response.setContentType("application/json;charset=utf-8");
        //获取输出流
        PrintWriter writer = response.getWriter();
        //构建输出数据
        UserDetails data = (UserDetails) authentication.getPrincipal();
        ResponseModel responseModel = ResponseModel.authSuccess(data.getUsername());
        //返回响应数据
        String json = JSON.toJSONString(responseModel);
        writer.println(json);
    }
}
