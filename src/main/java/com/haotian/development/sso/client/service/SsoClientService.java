package com.haotian.development.sso.client.service;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description: sso接口
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/29 10:06
 */
public interface SsoClientService {

  void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

}
