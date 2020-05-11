package com.page.security.browser;

import com.page.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler pageAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler pageAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll()
                .anyRequest()
                .authenticated()
                .and()
             .formLogin()
     //      http.httpBasic()
             .loginPage("/authentication/require")
             // loginProcessingUrl("/authentication/form")，spring就知道需要检验用户的相关信息，如姓名 密码 认证。
             .loginProcessingUrl("/authentication/form")
             .usernameParameter("username")
             .passwordParameter("password")
             .successHandler(pageAuthenticationSuccessHandler)
             .failureHandler(pageAuthenticationFailureHandler)
              .defaultSuccessUrl("/page-success.html");



    }
}
