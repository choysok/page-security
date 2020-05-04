package com.page.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;


//@Component  加上此注解 才能自动加入到Spring IoC容器里面
public class TimeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("时间过滤器Filter-----启动了-----");
        long startTime  =  new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("时间过滤器Filter耗时:"+(new Date().getTime()-startTime));
        System.out.println("时间过滤器Filter-----结束了-----");
        System.out.println();

    }

    @Override
    public void destroy() {

    }
}
