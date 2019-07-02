package com.haotian.config.filter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 访问地址log打印
 *
 * @author shanming.yang
 */
public class AccessLogFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(AccessLogFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("{}{}", "init...", this.getClass().getSimpleName());
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURL().toString();
        log.info("" + url + "  [" + req.getMethod() + "]");
        Map<String, String[]> map = req.getParameterMap();
        if (map.size() > 0) {
            StringBuffer param = new StringBuffer("");
            for (Map.Entry entry : map.entrySet()) {
                param.append("&" + (String) entry.getKey() + "=");
                String[] val = (String[]) entry.getValue();
                if (val.length == 1) {
                    param.append(val[0]);
                } else {
                    param.append(Arrays.toString(val));
                }
            }
            log.info("{}", param.toString());
        }

        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
