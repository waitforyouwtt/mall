package com.mowa.service.impl;

import com.mowa.service.OauthService;
import com.mowa.util.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    @Value("${auth.ttl}")
    private long ttl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret, String grantType) {
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("user-auth");
        URI uri = serviceInstance.getUri();
        String url=uri+"/oauth/token";*/
        String url = loadBalancerClient.choose("user-auth").getUri() + "/oauth/token/";
        //请求体
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        body.add("grant_type", grantType);
        //请求头
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        /*String authorization = "Basic " +
                new String( Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes()));*/
        String authorization = getHttpBasic( clientId,clientSecret );
        header.add("Authorization", authorization);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity(body, header);

        restTemplate.setErrorHandler( new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() !=400 && response.getRawStatusCode() !=401){
                    super.handleError( response );
                }
            }
        } );

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        Map map = response.getBody();
        if (map == null || map.get( "access_token" ) == null || map.get( "refresh_token" ) == null || map.get( "jti" ) == null){
            //申请令牌失败
            throw new RuntimeException( "申请令牌失败" );
        }
        //封装结果数据
        AuthToken token = new AuthToken();
        token.setAccessToken( (String) map.get( "access_token" ) );
        token.setRefreshToken( (String) map.get( "refresh_token" ) );
        token.setJti( (String)map.get( "jti" ) );

        //将jti作为redis 中的key,将jwt 作为redis 中的value 进行数据的存放
        stringRedisTemplate.boundValueOps(token.getJti()).set(token.getAccessToken(),ttl, TimeUnit.SECONDS);
        return token;
    }

    private String getHttpBasic(String clientId,String clientSecret){
        String value = clientId.concat( ":" ).concat( clientSecret );
        byte[] encode = Base64Utils.encode( value.getBytes() );
        return "Basec "+new String(encode);
    }
}
