package com.acme.biz.api.model;

import com.acme.biz.api.ApiRequest;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;

import javax.validation.*;
import javax.validation.bootstrap.GenericBootstrap;
import java.util.Set;

/**
 * @author : IceBlue
 * @date : 2025/4/15 22:15
 **/
public class UserTest {

    @Test
    public void testValidateUser(){
        GenericBootstrap bootstrap= Validation.byDefaultProvider();

        final Configuration<?> configuration = bootstrap.configure();

        MessageInterpolator targetInterpolator =configuration.getDefaultMessageInterpolator();

        configuration.messageInterpolator(new LocaleContextMessageInterpolator(targetInterpolator));

        final ValidatorFactory validatorFactory = configuration.buildValidatorFactory();
        final Validator validator = validatorFactory.getValidator();

        User user=new User();
        user.setName("aa");

        ApiRequest<User> request = new ApiRequest<>();
        request.setBody(user);


        final Set<ConstraintViolation<ApiRequest<User>>> constraintViolations = validator.validate(request);

        constraintViolations.forEach(cv->{
            System.out.println(cv.getPropertyPath()+":"+cv.getMessage());
        });
    }
}
