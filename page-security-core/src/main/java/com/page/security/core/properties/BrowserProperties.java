package com.page.security.core.properties;

import org.apache.commons.lang.StringUtils;

public class BrowserProperties {

    private String loginPage = "/page-signIn.html";

    private LoginType loginType  = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
