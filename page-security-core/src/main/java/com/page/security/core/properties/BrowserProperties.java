package com.page.security.core.properties;

import org.apache.commons.lang.StringUtils;

public class BrowserProperties {

    private String loginPage = "/signIn.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
