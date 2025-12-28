package com.vegetablesale.securityjwt.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

//@Component
@WebFilter(urlPatterns="/source/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Boolean loginInfo = (Boolean) session.getAttribute("loginInfo");
        if (Objects.isNull(loginInfo) || loginInfo != true){
            System.out.println("未登录");
            return;
        }
        filterChain.doFilter(request,servletResponse);
    }
}
