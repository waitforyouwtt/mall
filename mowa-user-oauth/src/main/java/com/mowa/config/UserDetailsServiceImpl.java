package com.mowa.config;

import com.mowa.Result;
import com.mowa.feign.UserFeign;
import com.mowa.user.pojo.UserInfo;
import com.mowa.util.UserJwt;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    private UserFeign userFeign;

    /**
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆客户端认证开始☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆

        /*用户信息认证*/
        if (StringUtils.isBlank(username)){
            return null;
        }

        /*客户端信息认证*/
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails == null){
                return null;
            }else{
                //静态方式：
                /*return new User(
                        //客户端id
                        username,
                        //客户端密钥
                        new BCryptPasswordEncoder().encode(clientDetails.getClientSecret()),
                        //客户端权限
                        AuthorityUtils.commaSeparatedStringToAuthorityList("")
                );*/
                //数据库查找方式
                return new User(
                        //客户端id
                        username,
                        //客户端密钥
                        new BCryptPasswordEncoder().encode(clientDetails.getClientSecret()),
                        //客户端权限
                        AuthorityUtils.commaSeparatedStringToAuthorityList("")
                );
            }
        }
        //☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆客户端认证结束☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
        //-----------------------------------用户认证开始--------------------------------------------------
        Result remote = userFeign.findByUserName(username);
        if (remote.getCode() != 20000){
            return null;
        }
        UserInfo userInfo = (UserInfo) remote.getData();
        if (userInfo == null ) {
            return null;
        }
        String pwd = userInfo.getPassword();
        //用户的角色信息
        String permissions = "admin,user";
        //创建User对象
        UserJwt userDetails = new UserJwt(username,new BCryptPasswordEncoder().encode(pwd),AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        return userDetails;
        //-----------------------------------用户认证结束--------------------------------------------------
    }
}
