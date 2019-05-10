package com.haotian.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 全局跨域过滤器
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/5/5 17:06
 */
@Component("crossOriginInterceptor")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CrossOriginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        if (logger.isDebugEnabled()) {
            logger.debug(">>CrossOriginInterceptor");
        }
        return super.preHandle(request, response, handler);
    }
}
