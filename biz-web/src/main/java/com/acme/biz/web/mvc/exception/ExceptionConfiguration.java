package com.acme.biz.web.mvc.exception;

import com.acme.biz.api.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * @author : IceBlue
 * @date : 2025/4/16 21:18
 **/
@RestControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(ValidationException.class)
    public ApiResponse<?> onValidationException(ValidationException e) {

        return ApiResponse.failed(null, e.getLocalizedMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        return ApiResponse.failed(null, e.getLocalizedMessage());
    }
}
