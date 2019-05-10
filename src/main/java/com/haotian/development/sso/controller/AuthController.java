package com.haotian.development.sso.controller;

import com.haotian.core.vo.BaseVO;
import com.haotian.development.sso.Constant;
import com.haotian.development.sso.component.JsonWebTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: sso认证
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/19 14:38
 */
@Api(tags = "认证操作相关接口")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JsonWebTokenUtils jwtUtil;

    @ApiOperation(value = "认证接口【已完成】", notes = "三方工具包或三方JS脚本或三方拦截器使用的验证接口，认证通过返回新的token，认证失败则返回returnCode=\"003\"")
    @PostMapping("token")
    public BaseVO token(@ApiParam("需要验证的token字符串") @RequestParam String token) throws Exception {
        BaseVO vo = new BaseVO();

        if (null == token) {
            return vo.errorParamReturn();
        }

        boolean valid = jwtUtil.isValid(token);

        // 认证成功，刷新token 并返回
        if (valid) {
            // 解析原有jwt的body信息
            Map<String, Object> stringObjectMap = jwtUtil.parseJWTtoMap(token);
            // 刷新token过期时间
            String jwtString = jwtUtil.getJWTString(null, stringObjectMap);
            // 封装返回VO
            Map<String, String> data = new HashMap<>();
            data.put(Constant.TOKEN_KEY, jwtString);
            vo.setData(data);
            return vo.successReturn();
        }
        return vo.errorAuthReturn();
    }


}
