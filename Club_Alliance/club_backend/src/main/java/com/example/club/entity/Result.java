package com.example.club.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    public static final String CODE_SUCCESS = "success";
    public static final String CODE_AUTH_ERROE = "auth_error";
    public static final String CODE_SYS_ERROR = "sys_error";


    private String code;
    private String msg;
    private Object data;

    public static  Result success() {
        return new Result(CODE_SUCCESS,"请求成功",null);
    }
    public static  Result success(Object data) {
        return new Result(CODE_SUCCESS,"请求成功",data);
    }

    public static Result error(String msg){
        return new Result(CODE_SYS_ERROR,msg,null);
    }
    public static  Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    public static Result error(){
        return new Result(CODE_SYS_ERROR,"系统错误",null);
    }
}
