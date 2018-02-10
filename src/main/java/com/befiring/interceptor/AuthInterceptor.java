package com.befiring.interceptor;

import com.befiring.annotations.IgnoreSecurity;
import com.befiring.exception.ErrorCode;
import com.befiring.exception.ErrorMessage;
import com.befiring.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod= (HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        if(method.isAnnotationPresent(IgnoreSecurity.class)){
            return true;
        }

        String token=httpServletRequest.getHeader("ACCESS_TOKEN");
        if(StringUtils.isEmpty(token)){
            throw new RequestException(ErrorCode.INVALID_TOKEN, ErrorMessage.INVALID_TOKEN);
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
