package com.acme.biz.web.mvc.method.annotation;

import com.acme.biz.api.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * 接口返回类型统一处理
 *
 * @author : IceBlue
 * @date : 2025/4/20 15:40
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
 **/
public class ApiResponseHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    private final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class)
                || returnType.hasMethodAnnotation(ResponseBody.class))
                && !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * @param returnValue:  返回的结果
     * @param returnType:
     * @param mavContainer:
     * @param webRequest:
     * @return void
     * @author IceBlue
     * @date 2025/4/20 15:58
     **/
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //标记已被处理过
        mavContainer.setRequestHandled(true);

        ApiResponse<?> apiResponse = ApiResponse.ok(returnValue);
        final ServletServerHttpResponse httpOutputMessage = createOutputMessage(webRequest);

        converter.write(apiResponse, MediaType.APPLICATION_JSON, httpOutputMessage);
    }

    protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
        HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        return new ServletServerHttpResponse(response);
    }
}
