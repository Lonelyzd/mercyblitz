package com.acme.biz.api.interfaces;

import com.acme.biz.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 用户服务接口(Open Feign、Dubbo等 公用)
 * @author : IceBlue
 * @date : 2025/4/6 15:07
 * @deprecated 该接口不再推荐使用，请使用 {@link UserLoginService} 或 {@link UserRegistrationService}
 *
 **/
@Deprecated
@RequestMapping("/user")
@FeignClient("${user.service.name}")
public interface UserService {

    @PostMapping("/register")
    Boolean registerUser(User user);
}
