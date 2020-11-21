package com.mowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/21 12:23
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
//注册到eureka 注册中心
@EnableEurekaClient
//排除数据库自动加载
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run( FileApplication.class, args );
    }

}
