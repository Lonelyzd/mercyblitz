package com.acme.biz.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;

import javax.validation.*;
import javax.validation.bootstrap.GenericBootstrap;
import java.util.Set;

/**
 * 公用的 Test 类
 *
 * @author : IceBlue
 * @date : 2025/4/16 21:56
 **/
public abstract class BaseTest {

    private Validator validator;

    @BeforeEach
    public void init() {
        GenericBootstrap bootstrap = Validation.byDefaultProvider();

        final Configuration<?> configuration = bootstrap.configure();

        MessageInterpolator targetInterpolator = configuration.getDefaultMessageInterpolator();

        configuration.messageInterpolator(new LocaleContextMessageInterpolator(targetInterpolator));

        final ValidatorFactory validatorFactory = configuration.buildValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    protected <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups){
        return this.validator.validate(object,groups);
    }
}
