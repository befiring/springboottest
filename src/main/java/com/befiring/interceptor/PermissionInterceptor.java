package com.befiring.interceptor;

import com.befiring.annotations.RequiredPermission;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
//        if(hasToken(handler))
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean hasToken(Object handler){
          if(handler instanceof HandlerMethod){
              HandlerMethod handlerMethod= (HandlerMethod) handler;
              RequiredPermission requiredPermission =handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
              if(requiredPermission == null){
                  requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
              }
              if(requiredPermission != null && !StringUtils.isEmpty(requiredPermission.value())){
                  return true;
              }
          }
        return false;
    }
}
