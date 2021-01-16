package com.mowa.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2021/1/14 23:02
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class JwtUtil {

    //@Value( "${mowa.jwt.token.expiration.time}" )
    private static final Long expirationTime = 3600000L;

    //@Value( "${mowa.jwt.token.security}" )
    private static final String securityKey = "0987654321-+=,.";
    /**
     * 用户登录成功后生成JWT
     * 使用HS256算法，私钥使用用户密码
     *@Param ttlMillis jwt 过期时间
     *@Param id 唯一标识
     * @return
     */
    public static String createJWT(String id,String subject,Long ttlMillis){
        //系统当前时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //如果
        if (ttlMillis == null){
            ttlMillis = expirationTime;
        }
        //令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成秘钥
        SecretKey secretKey = generalKey();

        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(id)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //签发者
                .setIssuer("admin")
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //设置签名使用的签名算法和签名使用的秘钥,也就是header那部分，jjwt已经将这部分内容封装好了。
                .signWith(SignatureAlgorithm.HS256, secretKey)
                //设置过期时间
                .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成加密秘钥
     * @return
     */
    private static SecretKey generalKey(){
        byte[] encodedKey = Base64.getEncoder().encode(securityKey.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
        return key;
    }


    /**
     * Token的解密
     * @param token 加密后的token
     * @return
     */
    public static Claims parseJWT(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        //得到DefaultJwtParser
        return Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
    }
}
