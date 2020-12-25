package com.mowa.handler;

import com.mowa.Result;
import com.mowa.enums.StatusCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 11:33
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        Result result = new Result();
        result.setFlag(false);
        result.setCode( StatusCodeEnum.EXCEPTION.getCode());
        result.setMessage(StatusCodeEnum.EXCEPTION.getMsg());
        result.setData(null);
        return result;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result myException(HttpServletRequest request, BusinessException e){
        e.printStackTrace();
        Integer code = e.getCode();
        String msg = e.getMessage();
        if (e.getCode() == null){
            code = StatusCodeEnum.EXCEPTION.getCode();
        }
        if (e.getMessage() == null){
            msg = StatusCodeEnum.EXCEPTION.getMsg();
        }
        Result result = new Result();
        result.setFlag(false);
        result.setCode(code);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }
}
