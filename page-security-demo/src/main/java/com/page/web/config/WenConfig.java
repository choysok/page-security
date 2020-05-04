package com.page.web.config;


import com.page.web.filter.TimeFilter;
import com.page.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.List;
//此配置类，用于配置过滤器类
@Configuration
public class WenConfig extends WebMvcConfigurerAdapter {//WebMvcConfigurerAdapter用于注册 Interceptor
     @Autowired
    private TimeInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Bean
       public FilterRegistrationBean timeFilter(){
           FilterRegistrationBean registrationBean  =  new FilterRegistrationBean();
           TimeFilter timeFilter =  new TimeFilter();
           registrationBean.setFilter(timeFilter);

           List<String> urls =  new ArrayList<>();
           urls.add("/*");

           registrationBean.setUrlPatterns(urls);


           return  registrationBean;

       }
}
