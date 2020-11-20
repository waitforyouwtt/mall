package com.mowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/20 22:02
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@EnableEurekaServer
@SpringBootApplication
public class MowaEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MowaEurekaApplication.class, args);
    }
}
