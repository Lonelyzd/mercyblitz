package com.acme.biz.web.client;

import com.acme.biz.api.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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

    private final HttpMessageConverter[] converters;

    public ValidatingClientHttpRequestInterceptor(Validator validator, HttpMessageConverter... converters) {
        this.validator = validator;
        this.converters = converters;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse clientHttpResponse = null;
        //前置处理
        beforeExecution(request, body);

        //请求处理
        clientHttpResponse = execution.execute(request, body);

        //后置处理
        return afterExecution(clientHttpResponse);
    }

    private void beforeExecution(HttpRequest request, byte[] body) {
        validateBean(request, body);
    }

    private void validateBean(HttpRequest request, byte[] body) {

        Class<?> bodyClass = resovleBodyClass(request.getHeaders());
        if (bodyClass != null) {
            final MappingJacksonInputMessage httpInputMessage = new MappingJacksonInputMessage(new ByteArrayInputStream(body), request.getHeaders());
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
    }


    private Class<?> resovleBodyClass(HttpHeaders headers) {
        // 临时传递 HTTP Header
        final List<String> classes = headers.remove("body-class");
        if (!ObjectUtils.isEmpty(classes)) {
            final String bodyClassName = classes.get(0);
            if (StringUtils.hasText(bodyClassName)) {
                return ClassUtils.resolveClassName(bodyClassName, null);
            }
        }
        return ApiResponse.class;

    }

    private MediaType resovleMediaType(MappingJacksonInputMessage httpInputMessage) {
        return httpInputMessage.getHeaders().getContentType();
    }

    private ClientHttpResponse afterExecution(ClientHttpResponse response) {
        return response;
    }
}
