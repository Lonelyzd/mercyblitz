package com.acme.biz.web.mvc.controller;

import com.acme.biz.api.interfaces.UserRegistrationService;
import com.acme.biz.api.model.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : IceBlue
 * @date : 2025/4/20 21:33
 **/
@RestController
public class UserRegistrationController implements UserRegistrationService {

    @Override
    public Boolean registerUser(User user) {
        return Boolean.TRUE;
    }
}
