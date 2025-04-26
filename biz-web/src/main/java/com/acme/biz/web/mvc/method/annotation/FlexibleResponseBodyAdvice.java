package com.acme.biz.web.mvc.method.annotation;

import com.acme.biz.api.HttpApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

//@RestControllerAdvice
public class FlexibleResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class)
                || returnType.hasMethodAnnotation(ResponseBody.class))
                && !HttpApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (Objects.isNull(body)) {
            return null;
        }
        if (body instanceof String) {
            return HttpApiResponse.ok(body);
        }
        if (body instanceof HttpApiResponse) {
            return body;
        }
        return  HttpApiResponse.ok(body);
    }
}