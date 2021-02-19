package com.mowa.feign.rollback;

import com.mowa.Result;
import com.mowa.feign.UserFeign;
import com.mowa.user.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserFeignRollBack implements UserFeign {
    @Override
    public Result<UserInfo> findByUserName(String userName) {
        return Result.error(500,"远程调用用户服务发生异常");
    }
}
