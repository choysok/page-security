package com.page.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("preHandle-----开启");

        System.out.println("类名："+((HandlerMethod)o).getBean().getClass().getName());
        System.out.println("方法名："+((HandlerMethod)o).getMethod().getName());

        httpServletRequest.setAttribute("startTime",new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle-----开启");

        Long startTime = (Long) httpServletRequest.getAttribute("startTime");

        System.out.println("time interceptor -----耗时："+(new Date().getTime() - startTime));

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion------------启动了");

        Long startTime = (Long) httpServletRequest.getAttribute("startTime");

        System.out.println("time interceptor -----耗时："+(new Date().getTime() - startTime));

        System.out.println("ex is "+e);
    }
}
