package com.acme.biz.web.mvc.controller;

import com.acme.biz.api.HttpApiRequest;
import com.acme.biz.api.HttpApiResponse;
import com.acme.biz.api.interfaces.UserRegistrationRestService;
import com.acme.biz.api.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : IceBlue
 * @date : 2025/4/17 21:21
 **/
@RestController
public class UserRegistrationRestController implements UserRegistrationRestService {


    @Override
    public HttpApiResponse<Boolean> registerUser(@RequestBody @Validated User user) {
        return HttpApiResponse.ok(Boolean.TRUE);
    }

    @Override
    public HttpApiResponse<Boolean> registerUser(@RequestBody @Validated HttpApiRequest<User> user) {
        return HttpApiResponse.ok(Boolean.TRUE);
    }

    //    @PostMapping(value = "/version", produces = "application/json;v=A")
//    public HttpApiResponse<String> version1() {
//        return HttpApiResponse.ok("v1");
//    }
    @PostMapping(value = "/version", produces = "application/json;v=1.0")
    public HttpApiResponse<String> version2() {

        //1. 设置请求头参数
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //2. 模拟表单参数 请求体携带参数
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username", "zhangsan");
        //3. 封装HttpEntity对象
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8802/testRestPost", requestEntity, String.class);



        return HttpApiResponse.ok("v2");
    }

    @PostMapping(value = "/version", produces = "application/json;v=1.1")
    public HttpApiResponse<String> version1() {
        return HttpApiResponse.ok("v2");
    }


}
