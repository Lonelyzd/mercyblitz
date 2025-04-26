package com.acme.biz.web.client;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Bean Validation 校验的实现 {@link ClientHttpRequestInterceptor}
 *
 * @author : IceBlue
 * @date : 2025/4/26 21:38
 **/
public class ValidatingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private final Validator validator;

    private final List<HttpMessageConverter<?>> converters;

    public ValidatingClientHttpRequestInterceptor(RestTemplate restTemplate, Validator validator) {
        this.validator = validator;
        this.converters = restTemplate.getMessageConverters();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse clientHttpResponse = null;
        //前置处理
        beforeExecution(request, body);

        //请求处理
        clientHttpResponse = execution.execute(request, body);

        //后置处理


        return afterExecution(request, body);
    }

    private void beforeExecution(HttpRequest request, byte[] body) {
        final MappingJacksonInputMessage httpInputMessage = new MappingJacksonInputMessage(new ByteArrayInputStream(body), request.getHeaders());

        Class<?> bodyClass = resovleBodyClass(httpInputMessage);
        MediaType mediaType = resovleMediaType(httpInputMessage);

        for (HttpMessageConverter converter : this.converters) {
            if (converter.canRead(bodyClass, mediaType)) {
                try {
                    Object bean = converter.read(bodyClass, httpInputMessage);
                    Set<ConstraintViolation<Object>> violations = this.validator.validate(bean);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }


    private Class<?> resovleBodyClass(MappingJacksonInputMessage httpInputMessage) {
        return null;
    }

    private MediaType resovleMediaType(MappingJacksonInputMessage httpInputMessage) {
        return httpInputMessage.getHeaders().getContentType();
    }

    private ClientHttpResponse afterExecution(HttpRequest request, byte[] body) {
        return null;
    }
}
