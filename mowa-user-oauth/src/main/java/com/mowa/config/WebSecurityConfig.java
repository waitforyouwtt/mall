package com.mowa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 忽略安全拦截的url
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/login",
                "/oauth/logout","/oauth/toLogin","/login.html","/css/**","/data/**","/fonts/**","/img/**","/js/**");
    }


    /***
     * 创建授权管理认证对象
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /***
     * 采用BCryptPasswordEncoder对密码进行编码
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //启用http基本身份验证
                .httpBasic()
                .and()
                //启用表单身份验证
                .formLogin()
                .and()
                //限制基于request请求访问
                .authorizeRequests()
                .anyRequest()
                //其他请求都需要经过验证
                .authenticated();

        //开启表单登录
       // http.formLogin().loginPage("/oauth/toLogin")//设置访问登录页面的路径
       //         .loginProcessingUrl("/oauth/login");//设置执行登录操作的路径
    }
}
