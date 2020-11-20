package com.mowa;

import com.mowa.enums.StatusCodeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/20 22:22
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
@ApiModel
public class Result<T> {
    private boolean flag;
    private Integer code;
    private String message;
    private T data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {}

    public static <T> Result<T> success(T data) {
        Result<T> response = new Result();
        response.setFlag( true );
        response.setCode( StatusCodeEnum.SUCCESS.getCode());
        response.setMessage(StatusCodeEnum.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }
    public static <T> Result<T> success() {
        Result<T> response = new Result();
        response.setFlag( true );
        response.setCode(StatusCodeEnum.SUCCESS.getCode());
        response.setMessage(StatusCodeEnum.SUCCESS.getMsg());
        response.setData(null);
        return response;
    }
    public static <T> Result<T> error(T data) {
        Result<T> response = new Result();
        response.setFlag( false );
        response.setCode(StatusCodeEnum.ERROR.getCode());
        response.setMessage(StatusCodeEnum.ERROR.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> Result<T> error() {
        Result<T> response = new Result();
        response.setFlag( false );
        response.setCode(StatusCodeEnum.ERROR.getCode());
        response.setMessage(StatusCodeEnum.ERROR.getMsg());
        response.setData(null);
        return response;
    }

    public static <T> Result<T> error(Integer code,String message) {
        Result<T> response = new Result();
        response.setFlag( false );
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
