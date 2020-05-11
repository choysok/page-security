package com.page.security.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//OncePerRequestFilter这个工具类保证过滤器只会被调用一次每次请求
public class ValidateCodeFilter extends OncePerRequestFilter {

     private AuthenticationFailureHandler authenticationFailureHandler;

     private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       if(StringUtils.equals("/authentication/form",httpServletRequest.getRequestURI()) && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
        try{
            validate(new ServletWebRequest(httpServletRequest));
        }catch (ValidateCodeException ex){
            authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,ex);
        }
       }
       filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);

    }
}
