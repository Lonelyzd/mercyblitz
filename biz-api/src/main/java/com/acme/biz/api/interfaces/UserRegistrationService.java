package com.acme.biz.api.interfaces;

import com.acme.biz.api.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 用户注册服务 REST 接口  (Open Feign、Dubbod等共用 )
 *
 * @author : IceBlue
 * @date : 2025/4/13 16:17
 **/

@DubboService
@RequestMapping("/user")
@FeignClient("${user-registration.service.name}")
public interface UserRegistrationService {

    @PostMapping("/register")
    Boolean registerUser(@RequestBody @Validated @Valid User user);

}
