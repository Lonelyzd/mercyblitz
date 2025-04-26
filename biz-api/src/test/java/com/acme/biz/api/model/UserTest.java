package com.acme.biz.api.model;

import com.acme.biz.api.HttpApiRequest;
import com.acme.biz.api.BaseTest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author : IceBlue
 * @date : 2025/4/15 22:15
 **/
public class UserTest  extends BaseTest {


    @Test
    public void testValidateUser(){

        User user=new User();
//        user.setName("aa");

        HttpApiRequest<User> request = new HttpApiRequest<>();
        request.setBody(user);

        final Set<ConstraintViolation<HttpApiRequest<User>>> constraintViolations = super.validate(request);

        constraintViolations.forEach(cv->{
            System.out.println(cv.getPropertyPath()+":"+cv.getMessage());
        });
    }
}
