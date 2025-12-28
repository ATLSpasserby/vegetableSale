package com.vegetablesale.securityjwt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private Integer age;
    private String IDcard;
    private String role;
    private Integer locked;
    private Integer enable;

}
