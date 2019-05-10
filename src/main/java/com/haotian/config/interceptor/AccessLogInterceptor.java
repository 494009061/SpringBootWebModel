package com.haotian.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * 访问地址log打印
 *
 * @author shanming.yang
 */
@Component("accessLogInterceptor")
@Order(Ordered.LOWEST_PRECEDENCE+1)
public class AccessLogInterceptor extends HandlerInterceptorAdapter {
    private Logger log = LoggerFactory.getLogger(AccessLogInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String url = request.getRequestURL().toString();
        log.info("" + url + "  [" + request.getMethod() + "]");
        Map<String, String[]> map = request.getParameterMap();
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
        return true;
    }

}
