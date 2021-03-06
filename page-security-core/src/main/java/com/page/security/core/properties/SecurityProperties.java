package com.page.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
//读取application.properties中page.security
@ConfigurationProperties(prefix = "page.security")
public class SecurityProperties {

    private BrowserProperties browser =  new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
