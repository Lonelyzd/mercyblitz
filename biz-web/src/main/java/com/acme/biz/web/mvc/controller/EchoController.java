package com.acme.biz.web.mvc.controller;

import com.acme.biz.api.ApiRequest;
import com.acme.biz.api.ApiResponse;
import com.acme.biz.api.model.User;
import com.acme.biz.web.client.ValidatingClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author : IceBlue
 * @date : 2025/4/27 21:59
 **/
@RestController
@RequestMapping("/echo")
public class EchoController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get/{message}")
    public ApiResponse<String> getEcho(@PathVariable String message) {
        return ApiResponse.ok(message);
    }

    @GetMapping("/get-call/{message}")
    public ApiResponse<String> getTemplateCall(@PathVariable String message) {
        return restTemplate.getForObject("http://localhost:{port}/echo/get/{message}", ApiResponse.class, port, message);
//        restTemplate.getForEntity("http://localhost:{port}/echo/{message}", ApiResponse.class,port, message);
    }


    @PostMapping("/post")
    public ApiResponse<User> postEcho(@RequestBody ApiRequest<User> userApi) {
        return ApiResponse.ok(userApi.getBody());
    }

    @PostMapping("/post-call")
    public ApiResponse<User> postTemplateCall(@RequestBody ApiRequest<User> userApi) {

        //1. 设置请求头参数
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //2. 模拟表单参数 请求体携带参数
        requestHeaders.addIfAbsent(ValidatingClientHttpRequestInterceptor.CLASS_NAME, userApi.getClass().getName());
        //3. 封装HttpEntity对象
        HttpEntity<ApiRequest<User>> requestEntity = new HttpEntity<>(userApi, requestHeaders);

        ResponseEntity<ApiResponse> apiResponseResponseEntity = restTemplate.postForEntity("http://localhost:{port}/echo/post", requestEntity, ApiResponse.class, port);
        return apiResponseResponseEntity.getBody();

    }

}
