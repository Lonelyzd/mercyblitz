package com.acme.biz.web.mvc.controller;

import com.acme.biz.api.ApiRequest;
import com.acme.biz.api.ApiResponse;
import com.acme.biz.api.interfaces.UserRegistrationRestService;
import com.acme.biz.api.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : IceBlue
 * @date : 2025/4/17 21:21
 **/
@RestController
public class UserRegistrationController implements UserRegistrationRestService {
    @Override
    public ApiResponse<Boolean> registerUser(@RequestBody @Validated User user) {
        return ApiResponse.ok(Boolean.TRUE);
    }

    @Override
    public ApiResponse<Boolean> registerUser(@RequestBody @Validated ApiRequest<User> user) {
        return ApiResponse.ok(Boolean.TRUE);
    }
}
