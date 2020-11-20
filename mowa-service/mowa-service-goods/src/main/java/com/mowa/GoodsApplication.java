package com.mowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 0:31
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
//注册到eureka 注册中心
@EnableEurekaClient
@SpringBootApplication
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run( GoodsApplication.class, args );
    }
}
