package com.acme.biz.web.mvc.controller;

import com.acme.biz.api.ApiRequest;
import com.acme.biz.api.ApiResponse;
import com.acme.biz.api.interfaces.UserRegistrationRestService;
import com.acme.biz.api.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : IceBlue
 * @date : 2025/4/17 21:21
 **/
@RestController
public class UserRegistrationRestController implements UserRegistrationRestService {


    @Override
    public ApiResponse<Boolean> registerUser(@RequestBody @Validated User user) {
        return ApiResponse.ok(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> registerUser(@RequestBody @Validated ApiRequest<User> user) {
        return ApiResponse.ok(Boolean.TRUE);
    }

//    @PostMapping(value = "/version", produces = "application/json;v=A")
//    public ApiResponse<String> version1() {
//        return ApiResponse.ok("v1");
//    }
    @PostMapping(value = "/version", produces = "application/json;v=1.0")
    public ApiResponse<String> version2() {
        return ApiResponse.ok("v2");
    }

    @PostMapping(value = "/version", produces = "application/json;v=1.1")
    public ApiResponse<String> version1() {
        return ApiResponse.ok("v2");
    }



}
