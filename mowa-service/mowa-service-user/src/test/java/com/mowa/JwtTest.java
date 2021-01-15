package com.mowa;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2021/1/14 22:19
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Slf4j
@Component
public class JwtTest extends UserInfoApplicationTest {

    @Test
    public void base64Encode() throws UnsupportedEncodingException {
        byte[] encode = Base64.getEncoder().encode( "abcdefg".getBytes() );
        String str    = new String( encode,"UTF-8" );
        log.info( "经过base64加密后的数据：{}",str );
    }

    @Test
    public void base64Decode() throws UnsupportedEncodingException {
        String decode = "YWJjZGVmZw==";
        byte[] decodeStr = Base64.getDecoder().decode( decode );
        String str = new String(decodeStr,"UTF-8");
        log.info( "经过base64解密后的数据：{}",str );
    }

    @Test
    public void createTest(){
        //构建jwt 令牌的对象
        JwtBuilder builder = Jwts.builder();
        //颁发者
        builder.setIssuer( "云澜" );
        //颁发时间
        builder.setIssuedAt( new Date(  ) );
        //过期时间
        builder.setExpiration( new Date( System.currentTimeMillis()+3600000 ) );
        //主题信息
        builder.setSubject( "令牌测试" );

        //自定义载荷信息
        Map<String,Object> map = new HashMap<>(  );
        map.put( "username","yunlan" );
        map.put( "company","魔蛙信息科技有限公司" );
        map.put( "address","上海" );

        builder.addClaims( map );

        //签名算法 &密钥
        builder.signWith( SignatureAlgorithm.HS256,"yunlan" );
        String token = builder.compact();
        log.info( "生成的token是:{}",token );
    }

    @Test
    public void parseJwt(){
        //token 分为三部分：头部，载荷，签名，以.分隔
        String token  = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiLkupHmvpwiLCJpYXQiOjE2MTA2MzQ2MTgsImV4cCI6MTYxMDYzODIxOCwic3ViIjoi5Luk54mM5rWL6K-VIiwiYWRkcmVzcyI6IuS4iua1tyIsImNvbXBhbnkiOiLprZTom5nkv6Hmga_np5HmioDmnInpmZDlhazlj7giLCJ1c2VybmFtZSI6Inl1bmxhbiJ9.Ag4virZGjC9imOjPyZf9A1uKs3x2nghSN08j51NitEg";
        Claims claims = Jwts.parser()
                .setSigningKey( "yunlan" )
                .parseClaimsJws( token )
                .getBody();
        log.info( "解析token得到的数据：{}", JSONUtil.toJsonStr( claims.toString() ) );

    }
}
