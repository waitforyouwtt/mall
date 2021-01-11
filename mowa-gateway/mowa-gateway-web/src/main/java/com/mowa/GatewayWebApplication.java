package com.mowa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2021/1/7 22:49
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
//注册到eureka 注册中心
@EnableEurekaClient
@SpringBootApplication
@Slf4j
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run( GatewayWebApplication.class, args );
    }

    /**
     * 创建用户唯一标识，使用IP作为用户唯一标识，来根据IP进行限流操作
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver useKeyResolver(){
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String ipHost = exchange.getRequest().getRemoteAddress().getHostString();
                log.info("网关拦截请求获取到的ipHost是：{}",ipHost);
                return Mono.just(  ipHost);
            }
        };
    }

}
