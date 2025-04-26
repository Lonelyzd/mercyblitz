package com.acme.biz.web.mvc.exception;

import com.acme.biz.api.HttpApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;


/**
 * @author : IceBlue
 * @date : 2025/4/16 21:18
 **/
@RestControllerAdvice
public class ExceptionConfiguration {
    @ExceptionHandler(ValidationException.class)
    public HttpApiResponse<?> onValidationException(ValidationException e) {
        return HttpApiResponse.failed(null, e.getLocalizedMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpApiResponse<?> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return HttpApiResponse.failed(null, e.getLocalizedMessage());
    }
}
