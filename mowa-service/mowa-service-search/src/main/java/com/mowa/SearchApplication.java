package com.mowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-06
 */
//实现feign 服务间通信
@EnableDiscoveryClient
@EnableFeignClients
//注册到eureka 注册中心
@EnableEurekaClient
//排除数据库自动加载
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run( SearchApplication.class, args );
    }
}
