package com.acme.biz.web.mvc.config;

import com.acme.biz.web.client.ValidatingClientHttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;

/**
 * {@link RestTemplate}
 *
 * @author : IceBlue
 * @date : 2025/4/27 21:26
 **/
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {

    @Bean
    public ClientHttpRequestInterceptor validatingClientHttpRequestInterceptor( Validator validator) {
        return new ValidatingClientHttpRequestInterceptor(validator,mappingJackson2HttpMessageConverter() );
    }
    @Bean
    public RestTemplate restTemplate(List<ClientHttpRequestInterceptor> interceptors) {
        List<HttpMessageConverter<?>> converters = Arrays.asList(mappingJackson2HttpMessageConverter());

        final RestTemplate restTemplate = new RestTemplate(converters);
        ClientHttpRequestFactory requestFactory = buildClientHttpRequestFactory(interceptors);

        restTemplate.setRequestFactory(requestFactory);

        //TODO 增加 ResponseErrorHandler
        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    private ClientHttpRequestFactory buildClientHttpRequestFactory(List<ClientHttpRequestInterceptor> interceptors) {
        //TODO 提换 SimpleClientHttpRequestFactory
        final SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        return new InterceptingClientHttpRequestFactory(simpleClientHttpRequestFactory, interceptors);
    }


}
