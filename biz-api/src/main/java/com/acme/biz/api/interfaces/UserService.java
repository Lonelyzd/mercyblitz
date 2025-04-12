package com.acme.biz.api.interfaces;

import com.acme.biz.api.model.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 用户服务接口(Open Feign、Dubbo等 公用)
 * @author : IceBlue
 * @date : 2025/4/6 15:07
 **/
@FeignClient("${userservice.name}")
@RequestMapping("/user")
@DubboService
public interface UserService {

    @PostMapping("/register")
    Boolean registerUser(User user);
}
