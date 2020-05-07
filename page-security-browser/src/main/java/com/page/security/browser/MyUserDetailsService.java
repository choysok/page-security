package com.page.security.browser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger  = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("loginName："+s);
      //  return new User(s,"123", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //passwordEncoder.encode("123") 。这个动作应该是注册的时候做的。
        String encode = passwordEncoder.encode("123");
        System.out.println("this is password:"+encode);

        return new User(s,encode,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
