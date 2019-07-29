package com.haotian.config.filter;

import com.haotian.core.filter.AccessLogFilter;
import com.haotian.core.filter.CrossOriginFilter;
import com.haotian.core.filter.SsoClientFilter;
import com.haotian.development.sso.client.service.SsoClientService;
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

    private final SsoClientService ssoClientService;

    @Autowired
    public GlobalFilterRegistrationConfig(SsoClientService ssoClientService) {
        this.ssoClientService = ssoClientService;
    }

    @Bean
    public FilterRegistrationBean crossOriginFilter() {
        FilterRegistrationBean<CrossOriginFilter> registration = new FilterRegistrationBean<>(new CrossOriginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("crossOriginFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public FilterRegistrationBean accessLogFilter() {
        FilterRegistrationBean<AccessLogFilter> registration = new FilterRegistrationBean<>(new AccessLogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("accessLogFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registration;
    }


    /**
     * SSO client 角色 拦截器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean ssoFilterRegistration() {
        SsoClientFilter ssoClientFilter = new SsoClientFilter();
        ssoClientFilter.setService(ssoClientService);
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ssoClientFilter);
        registration.addUrlPatterns("/*");
        registration.setName("ssoFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
        return registration;
    }


}
