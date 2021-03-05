package com.mowa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * @Author 凤凰小哥哥
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //数据源，用于从数据库获取数据进行认证操作
    @Autowired
    private DataSource dataSource;
    //jwt 令牌转换器
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    //spring security用户自定义授权认证类
    @Autowired
    private UserDetailsService userDetailsService;
    //授权认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;
    //令牌持久化存储接口
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;


    /***
     * 客户端信息配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.inMemory()
               //客户端id
               .withClient( "mowa666" )
               //密钥
               .secret( "mowa666" )
               //重定向地址
               .redirectUris( "http://localhost" )
               //访问令牌有效期
               .accessTokenValiditySeconds( 60 )
               //刷新令牌有效期
               .refreshTokenValiditySeconds( 60 )
               .authorizedGrantTypes(
                       //根据授权码生成令牌
                       "authorization_code",
                       //客户端认证
                       "client_credentials",
                       //刷新令牌
                       "refresh_token",
                       //密码方式认证
                       "password"
               ).scopes( "app" ); //客户端范围，名称自定义，但必填
    }

    /**
     * 授权服务器端点配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
      endpoints.accessTokenConverter( jwtAccessTokenConverter )
              //认证管理器
              .authenticationManager( authenticationManager )
              //令牌存储
              .tokenStore( tokenStore )
              //用户信息service
              .userDetailsService( userDetailsService );
    }

    /**
     *  授权服务器的安全配置
     * @param oauthServer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer){
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder( new BCryptPasswordEncoder(  ) )
                .tokenKeyAccess( "permitAll()" )
                .checkTokenAccess( "isAuthenticated()" );
    }

    @Bean("keyProp")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    //客户端配置
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /****
     * JWT令牌转换器
     * @param customUserAuthenticationConverter
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                //证书路径 changgou.jks
                keyProperties.getKeyStore().getLocation(),
                //证书秘钥 mowa
                keyProperties.getKeyStore().getSecret().toCharArray())
                .getKeyPair(
                        //证书别名 changgou
                        keyProperties.getKeyStore().getAlias(),
                        //证书密码 changgou
                        keyProperties.getKeyStore().getPassword().toCharArray());
        converter.setKeyPair(keyPair);
        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }
}
