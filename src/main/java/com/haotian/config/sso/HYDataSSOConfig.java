package com.haotian.config.sso;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: ZhangPeng
 * @email zhangpeng@hiynn.com
 * @date: 2019/5/11 23:11
 */
@Configuration
@PropertySource("classpath:conf/hydata-sso.properties")
public class HYDataSSOConfig {

    /**
     * sso拦截器属性声明
     */
    @Value("${hydata.sso.isOpen}")
    private String hydataSsoIsOpen;
    @Value("${hydata.sso.serverUrl}")
    private String hydataSsoServerUrl;
    @Value("${hydata.sso.isCross}")
    private String hydataSsoIsCross;
    @Value("${hydata.sso.skipUris}")
    private String hydataSsoSkipUris;

    public String getHydataSsoIsOpen() {
        return hydataSsoIsOpen;
    }

    public void setHydataSsoIsOpen(String hydataSsoIsOpen) {
        this.hydataSsoIsOpen = hydataSsoIsOpen;
    }

    public String getHydataSsoServerUrl() {
        return hydataSsoServerUrl;
    }

    public void setHydataSsoServerUrl(String hydataSsoServerUrl) {
        this.hydataSsoServerUrl = hydataSsoServerUrl;
    }

    public String getHydataSsoIsCross() {
        return hydataSsoIsCross;
    }

    public void setHydataSsoIsCross(String hydataSsoIsCross) {
        this.hydataSsoIsCross = hydataSsoIsCross;
    }

    public String getHydataSsoSkipUris() {
        return hydataSsoSkipUris;
    }

    public void setHydataSsoSkipUris(String hydataSsoSkipUris) {
        this.hydataSsoSkipUris = hydataSsoSkipUris;
    }
}
