package com.mowa.handler;

import lombok.Data;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 11:35
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class BusinessException extends RuntimeException{
    private Integer code;
    private String  msg ;

    public BusinessException(String msg){
        super(msg);
    }
    public BusinessException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg  = msg;
    }
}
