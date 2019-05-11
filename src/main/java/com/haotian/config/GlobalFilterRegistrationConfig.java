package com.haotian.config;

//import com.hiynn.core.filter.HYDataSSOFilter;

import com.haotian.config.filter.AccessLogFilter;
import com.haotian.config.filter.CrossOriginFilter;
import com.haotian.config.sso.HYDataSSOConfig;
import com.hiynn.core.filter.HYDataSSOFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 全局配置
 *
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/19 14:08
 */
@Configuration
public class GlobalFilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean crossOriginFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CrossOriginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("crossOriginFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean accessLogFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new AccessLogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("accessLogFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registration;
    }

    @Autowired
    HYDataSSOConfig ssoConfig;
    /**
     * SSO client 角色 拦截器 本demo 提供了sso 认证中心功能 正常使用中可分开使用
     * @return
     */
    @Bean
    public FilterRegistrationBean ssoFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean(new HYDataSSOFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("isOpen",ssoConfig.getHydataSsoIsOpen());
        registration.addInitParameter("serverUrl",ssoConfig.getHydataSsoServerUrl());
        registration.addInitParameter("isCross",ssoConfig.getHydataSsoIsCross());
        registration.addInitParameter("skipUris",ssoConfig.getHydataSsoSkipUris());
        registration.setName("ssoFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE+2);
        return registration;
    }


}
