package com.acme.biz.web.servlet.idempotent;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** 幂等性过滤器，在微服务环境下需要使用SpringSession
 * @author : IceBlue
 * @date : 2025/4/21 21:39
 **/
public class IdempotentFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getParameter("token");

        final HttpSession httpSession = request.getSession(false);

        final Object value = httpSession.getAttribute(token);

        if (value == null) {
            //抛出异常
            throw new ServletException("幂等性校验失败");
        }

        //设置状态
        httpSession.setAttribute(token, token);
        try {
            //处理逻辑
            filterChain.doFilter(request, response);
        } finally {
            //移除状态
            httpSession.removeAttribute(token);
        }
    }
}
