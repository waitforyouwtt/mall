package com.mowa.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    /**
     * 全局拦截
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取用户令牌信息
        //1.头文件中Header获取令牌
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //boolean：true 令牌在头文件Header中，false,令牌不在头文件Header中，将令牌封装到Header中，再传递给其他微服务
        boolean hasToken = true;

        //2.从参数中获取令牌
        if (StringUtils.isBlank(token)){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
        }
        //3.cookie 中获取令牌
        if (StringUtils.isBlank(token)){
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (httpCookie != null){
                token = httpCookie.getValue();
            }
        }
        //如果没有令牌，则拦击
        if (StringUtils.isBlank(token)){
            //设置状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //相应空数据
            return response.setComplete();
        }
        //如果有令牌，校验令牌是否有效:无效拦截，有效则放行
        try{
            JwtUtil.parseJWT(token);
        }catch (Exception e){
            //设置状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //相应空数据
            return response.setComplete();
        }
        //将令牌封装到Header中，再传递给其他微服务
        request.mutate().header( AUTHORIZE_TOKEN,token );
        return chain.filter(exchange);
    }

    /**
     * 排序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
