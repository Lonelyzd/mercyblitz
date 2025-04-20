package com.acme.biz.web.mvc.config;

import com.acme.biz.web.mvc.method.annotation.ApiResponseHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : IceBlue
 * @date : 2025/4/20 16:46
 **/
@Configuration
public class WebMvcConfiguration {

    @Autowired
    public void resetRequestMappingHandlerAdapter(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        final List<HandlerMethodReturnValueHandler> oldReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();

        if (oldReturnValueHandlers != null) {
            List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(oldReturnValueHandlers);
            //放在List 0号位置，提高优先级
            newReturnValueHandlers.add(0,new ApiResponseHandlerMethodReturnValueHandler());
            requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
        }
    }
}
