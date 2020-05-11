package com.page.security.core.validate.code;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 7565560291408909196L;


    public ValidateCodeException(String msg) {
        super(msg);
    }
}

