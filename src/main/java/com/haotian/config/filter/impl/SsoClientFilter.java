package com.haotian.config.filter.impl;

import com.haotian.development.sso.client.service.SsoClientService;
import com.haotian.development.sso.client.service.impl.SsoClientServiceImpl;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description: 验证拦截器, 用户继承拦截器 自行实现跳过哪些URL 本拦截器只负责提供 权限校验， 用户实现 重写doFilter 方法后 处理完自己的业务 需要调用super 的 doFilter
 * 进行 权限验证
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/25 16:05
 */
public class SsoClientFilter implements Filter {


    private SsoClientService service;


    public void setService(SsoClientService service) {
        this.service = service;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        service.doFilter(servletRequest, servletResponse, filterChain);
    }

    @Override
    public void destroy() {
        service = null;
    }
}
