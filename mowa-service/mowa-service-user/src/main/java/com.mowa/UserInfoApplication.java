package com.mowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2021/1/12 22:06
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
//注册到eureka 注册中心
@EnableEurekaClient
@MapperScan(basePackages = {"com.mowa.dao"})
@SpringBootApplication
public class UserInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run( UserInfoApplication.class, args );
    }
}
