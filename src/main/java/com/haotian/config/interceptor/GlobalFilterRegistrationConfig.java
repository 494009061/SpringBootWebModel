package com.haotian.config.interceptor;

//import com.hiynn.core.filter.HYDataSSOFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;

/**
 * 全局配置
 * 目前 Ordered.HIGHEST_PRECEDENCE 为 +1
 * 使用者请及时更新Ordered状态 下次排序为 +2
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/19 14:08
 */
@Configuration
//@PropertySource("classpath:conf/hydata-sso.properties")
public class GlobalFilterRegistrationConfig {


    /**
     * sso拦截器属性声明
      */
   /* @Value("${hydata.sso.isOpen}")
    public String hydataSsoIsOpen;
    @Value("${hydata.sso.serverUrl}")
    public String hydataSsoServerUrl;
    @Value("${hydata.sso.isCross}")
    public String hydataSsoIsCross;
    @Value("${hydata.sso.skipUris}")
    public String hydataSsoSkipUris;*/

    /**
     * SSO client 角色 拦截器 本demo 提供了sso 认证中心功能 正常使用中可分开使用
     * @return
     */
  /*  @Bean
    public FilterRegistrationBean ssoFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HYDataSSOFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("isOpen",hydataSsoIsOpen);
        registration.addInitParameter("serverUrl",hydataSsoServerUrl);
        registration.addInitParameter("isCross",hydataSsoIsCross);
        registration.addInitParameter("skipUris",hydataSsoSkipUris);
        registration.setName("ssoFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE+2);
        return registration;
    }*/

}
