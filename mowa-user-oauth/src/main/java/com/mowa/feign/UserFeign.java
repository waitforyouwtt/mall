package com.mowa.feign;

import com.mowa.Result;
import com.mowa.feign.rollback.UserFeignRollBack;
import com.mowa.user.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user",fallback = UserFeignRollBack.class)
//@FeignClient(name = "user",  fallback = UserFeignRollBack.class,url = "${user-url}")
public interface UserFeign {

    @GetMapping("user/findByUserName")
    Result<UserInfo> findByUserName(@RequestParam("userName") String userName);
}
