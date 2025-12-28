package com.vegetablesale.securityjwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityJwtApplicationTests {

    @Test
    void contextLoads() {

        String password = "123456";

//        //NoOpPasswordEncoder不对密码进行加密
//        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
//        String afterPassword = passwordEncoder.encode(password);
//        System.out.println("password:"+afterPassword);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String afterPassword = passwordEncoder.encode(password);
        System.out.println("password:"+afterPassword);

        //$2a$10$yAWWI1XT72GjIY13c4ySZeCUup0nOFvytUWjomoutIv8e8yvIVTIO

    }

}
