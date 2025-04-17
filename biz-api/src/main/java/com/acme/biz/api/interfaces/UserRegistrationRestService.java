package com.acme.biz.api.interfaces;

import com.acme.biz.api.ApiRequest;
import com.acme.biz.api.ApiResponse;
import com.acme.biz.api.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/** 用户注册服务 REST 接口  (Open Feign、Dubbod等共用 )
 * @author : IceBlue
 * @date : 2025/4/13 16:17
 **/

@DubboService
@RequestMapping("/api/user")
@FeignClient("${user-registration.rest-service.name}")
public interface UserRegistrationRestService {

    @PostMapping("/register/v1")
    ApiResponse<Boolean> registerUser(@RequestBody @Validated User user);

    @PostMapping("/register/v2")
    ApiResponse<Boolean> registerUser(@RequestBody @Validated ApiRequest<User> user);
}
