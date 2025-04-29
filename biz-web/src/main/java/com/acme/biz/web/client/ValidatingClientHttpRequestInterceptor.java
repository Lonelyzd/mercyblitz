package com.acme.biz.web.client;

import com.acme.biz.api.ApiRequest;
import com.acme.biz.api.model.User;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
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
public class ValidatingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor, Ordered {
    private final Validator validator;

    private final HttpMessageConverter[] converters;
    public static final String CLASS_NAME ="body-class";

    public ValidatingClientHttpRequestInterceptor(Validator validator, HttpMessageConverter... converters) {
        this.validator = validator;
        this.converters = converters;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = null;
        // 前置处理
        boolean valid = beforeExecute(request, body);
        HttpHeaders headers = request.getHeaders();
        headers.add("validation-result", Boolean.toString(valid));
        // 请求处理 (next interceptor)
        response = execution.execute(request, body);
        // 后置处理
        return afterExecute(response);
    }

    private ClientHttpResponse handleError(HttpRequest request, byte[] body) {
        return null;
    }

    private boolean beforeExecute(HttpRequest request, byte[] body) {
        return validateBean(request, body);
    }

    private boolean validateBean(HttpRequest request, byte[] body) {
        // FastJSON auto-type
        Class<?> bodyClass = resolveBodyClass(request.getHeaders());
        if (bodyClass != null) {
            HttpInputMessage httpInputMessage = new MappingJacksonInputMessage(new ByteArrayInputStream(body), request.getHeaders());
            MediaType mediaType = resolveMediaType(httpInputMessage);
            for (HttpMessageConverter converter : converters) {
                if (converter.canRead(bodyClass, mediaType)) {
                    try {
                        Object bean = converter.read(bodyClass, httpInputMessage);
                        ApiRequest<User> userApiRequest = new ApiRequest<>();
                        userApiRequest.setBody(new User());

                        Set<ConstraintViolation<Object>> violations = validator.validate(bean);
                        if (!violations.isEmpty()) {
                            return false;
                        }
                        // TODO
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return true;
    }

    private Class<?> resolveBodyClass(HttpHeaders httpHeaders) {
        // 临时传递 HTTP Header
        List<String> classes = httpHeaders.remove(CLASS_NAME);
        if (!ObjectUtils.isEmpty(classes)) {
            String bodyClassName = classes.get(0);
            if (StringUtils.hasText(bodyClassName)) {
                return ClassUtils.resolveClassName(bodyClassName, null);
            }
        }
        return null;
    }


    private MediaType resolveMediaType(HttpInputMessage httpInputMessage) {
        HttpHeaders httpHeaders = httpInputMessage.getHeaders();
        return httpHeaders.getContentType();
    }

    private ClientHttpResponse afterExecute(ClientHttpResponse response) {
        // TODO
        return response;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
