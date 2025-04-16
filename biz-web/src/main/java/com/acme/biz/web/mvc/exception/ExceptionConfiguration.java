package com.acme.biz.web.mvc.exception;

import com.acme.biz.api.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.ValidationException;

/**
 * @author : IceBlue
 * @date : 2025/4/16 21:18
 **/
@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(ValidationException.class)
    public ApiResponse<?> onValidationException(ValidationException e) {

        return ApiResponse.failed(null, e.getLocalizedMessage());
    }
}
