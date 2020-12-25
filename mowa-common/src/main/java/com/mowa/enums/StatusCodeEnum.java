package com.mowa.enums;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/20 22:27
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public enum StatusCodeEnum {
    SUCCESS(20000,"成功"),
    ERROR(20001,"失败"),
    LOGIN_ERROR(20002,"用户名或密码错误"),
    ACCESS_ERROR(20003,"权限不足"),
    REMOTE_ERROR (20004,"远程调用失败"),
    REPEAT_ERROR (20005,"重复操作"),
    PARAMS_ERROR (20006,"参数异常"),
    EXCEPTION(50000,"系统异常"),
    ;

    StatusCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String  msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
