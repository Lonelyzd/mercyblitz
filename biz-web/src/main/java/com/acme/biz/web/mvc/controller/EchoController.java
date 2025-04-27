package com.acme.biz.web.mvc.controller;

import com.acme.biz.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{message}")
    public ApiResponse<String> echo(@PathVariable String message) {
        return ApiResponse.ok(message);
    }

    @GetMapping("/rest-template/{message}")
    public ApiResponse<String> restTemplateCall(@PathVariable String message) {
        return restTemplate.getForObject("http://localhost:{port}/echo/{message}", ApiResponse.class,port, message);
    }



}
