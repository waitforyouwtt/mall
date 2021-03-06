package com.mowa.controller;

import com.mowa.Result;
import com.mowa.enums.StatusCodeEnum;
import com.mowa.service.OauthService;
import com.mowa.util.AuthToken;
import com.mowa.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private OauthService oauthService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    private static final String GRANT_TYPE = "password";//授权模式 密码模式

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    //Cookie生命周期
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @GetMapping("/toLogin")
    public String toLogin(@RequestParam(value = "FROM",required = false,defaultValue = "") String from, Model model){
        model.addAttribute("from",from);
        return "login";
    }

    /**
     * 密码模式  认证.
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result<Map> login(String username, String password, HttpServletResponse response) throws Exception {
        //登录 之后生成令牌的数据返回
        AuthToken token = oauthService.login(username, password, clientId, clientSecret, "authorization_code");
        //将jti的值存入cookie中
        this.saveJtiToCookie(token.getJti(),response);
        return new Result(true, StatusCodeEnum.SUCCESS.getCode(), "令牌生成成功",token.getJti());
    }

    private void saveJtiToCookie(String jti,HttpServletResponse response){
        //HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","Authorization",jti,cookieMaxAge,false);
    }
}
