package com.mowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-05
 */
//注册到eureka 注册中心
@EnableEurekaClient
@MapperScan(basePackages = {"com.mowa.dao"})
@SpringBootApplication
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run( ContentApplication.class, args );
    }
}
