package com.vegetablesale.securityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.vegetablesale.securityjwt.entity.ResponseModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //设置响应格式为json，并且设置为中文防止乱码
        response.setContentType("application/json;charset=utf-8");
        //获取输出流
        PrintWriter writer = response.getWriter();
        //构建输出数据,获取exception的错误信息并根据当前国家进行语言本地化
        String data = exception.getLocalizedMessage();
        ResponseModel responseModel = ResponseModel.authFailed(data);
        //返回响应数据
        String json = JSON.toJSONString(responseModel);
        writer.println(json);
    }
}
