package com.haotian.development.sso.client.service.impl;

import com.haotian.core.entity.HttpResponse;
import com.haotian.core.util.HttpUtils;
import com.haotian.core.vo.BaseVO;
import com.haotian.development.sso.client.Constant;
import com.haotian.development.sso.client.service.SsoClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: hydata sso service impl
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/29 10:17
 */
@Service
@PropertySource("classpath:conf/hydata-sso.properties")
public class SsoClientServiceImpl implements SsoClientService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * sso拦截器属性声明
     */
    @Value("${sso.client.isOpen}")
    private boolean ssoClientIsOpen;
    @Value("${sso.client.serverUrl}")
    private String ssoClientServerUrl;
    @Value("${sso.client.isCross}")
    private boolean ssoClientIsCross;
    @Value("${sso.client.skipUris}")
    private String ssoClientSkipUris;

    private void info(String fux, Object... message) {
        if (logger.isInfoEnabled()) {
            logger.info(fux, message);
        }
    }

    private void debug(String fux, Object... message) {
        if (logger.isDebugEnabled()) {
            logger.debug(fux, message);
        }
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 如果没有开启filter 或配置文件错误则跳过
        if (!ssoClientIsOpen) {
            info("{}", "ssoClientIsClose");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            info("{}", "ssoClientIsOpen");
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            // 是否负责跨域
            if (ssoClientIsCross) {
                info("{}", "ssoClientIsCross");
                // 是否由本filter负责跨域
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.setHeader("Access-Control-Allow-Headers", "*");
                resp.setHeader("Access-Control-Allow-Methods", "*");
                resp.setHeader("Access-Control-Allow-Credentials", "true");
                resp.setHeader("Access-Control-Max-Age", "3600");
            }

            // 检测url
            String requestURI = req.getRequestURI();

            // 默认不跳过
            boolean matches = false;

            // 如果配置了跳过uri
            if (ssoClientSkipUris != null) {
                matches = requestURI.matches("^(.*(" + ssoClientSkipUris + ").*)?$");
            }

            info("{}:{}", "SsoClientSkipUri", matches);

            // 匹配到当前uri 就跳过
            if (matches) {
                filterChain.doFilter(servletRequest, resp);
                return;
            } else {
                // 验证
                String token = req.getHeader(Constant.TOKEN_KEY_CLIENT);
                info("{}:{}", Constant.TOKEN_KEY_CLIENT, token);
                if (null != token) {

                    Map<String, String> param = new HashMap<>();
                    param.put(Constant.TOKEN_KEY_CLIENT, token);
                    HttpResponse response = HttpUtils.post(ssoClientServerUrl, param, HttpUtils.UTF8);


                    info("{}:{}", "authTokenResponse", response.toString());
                    if (response.getCode() == HttpStatus.OK.value()) {
                        BaseVO vo = response.toObject(BaseVO.class);
                        if (vo != null) {
                            if (BaseVO.Code.RETURN_CODE_SUCCESS.equals(vo.getReturnCode())) {
                                resp.addHeader(Constant.TOKEN_KEY_SERVER,vo.dataCast(String.class));
                                filterChain.doFilter(servletRequest, resp);
                                return;
                            }
                        }
                    }
                }
                info("{}", "auth token error");
                // 如果验证失败 等其他问题  返回回错误码 403 并拦截请求
                try (PrintWriter writer = resp.getWriter();) {
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    writer.print(BaseVO.errorAuth());
                    resp.setStatus(HttpStatus.FORBIDDEN.value());
                }
            }
        }
    }
}
