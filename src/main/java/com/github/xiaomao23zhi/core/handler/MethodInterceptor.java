package com.github.xiaomao23zhi.core.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Component
public class MethodInterceptor implements HandlerInterceptor {

    private final String[] allowedMethods = new String[]{"PUT", "POST", "GET", "DELETE", "OPTIONS"};

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        if (Arrays.stream(allowedMethods).noneMatch(x -> x.equals(request.getMethod()))) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.setHeader("Allow", "PUT, POST, GET, DELETE, OPTIONS");
            response.setContentType("message/http");
            response.getOutputStream().println(request.getMethod() + " method not allowed");
            response.getOutputStream().flush();
            return false;
        }
        return true;
    }
}