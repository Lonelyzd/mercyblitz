package com.acme.biz.api.interfaces;

import com.acme.biz.api.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 用户登录服务接口 (Open Feign、Dubbod等共用 )
 *
 * @author : IceBlue
 * @date : 2025/4/13 16:16
 **/
@DubboService
@RequestMapping("/user")
@FeignClient("${user-login.service.name}")
public interface UserLoginService {

    @PostMapping("/login")
    @Deprecated
    User login(Map<String, Object> context);
}
