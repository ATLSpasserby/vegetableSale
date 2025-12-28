package com.vegetablesale.securityjwt.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/loginSource")
    public String login(HttpSession session){
        session.setAttribute("loginInfo",true);
        System.out.println("session:"+session.getAttribute("loginInfo"));
        return "this is login";
    }

    @GetMapping("/source/source1")
    public String source1(){
        return "this is source1";
    }

    @GetMapping("/source/source2")
    public String source2(){
        return "this is source2";
    }

    @GetMapping("/logoutSource")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        System.out.println("session:"+session.getAttribute("loginInfo"));
        return "logout success";
    }
}
