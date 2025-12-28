package com.vegetablesale.securityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel<T> {

    private int code;

    private String message;

    private T data;

    public static <T> ResponseModel<T> authSuccess(T data){
        return new ResponseModel<>(200,"Security认证成功",data);
    }

    public static <T> ResponseModel<T> authFailed(T data){
        return new ResponseModel<>(401,"Security认证失败",data);
    }
}
