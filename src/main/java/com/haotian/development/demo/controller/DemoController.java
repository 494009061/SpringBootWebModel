package com.haotian.development.demo.controller;

import com.haotian.core.util.HttpUtils;
import com.haotian.core.vo.BaseVO;
import com.haotian.development.demo.service.DemoService;
import com.haotian.development.entity.DemoTest;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * simple demo controller
 *
 * @author: Mr.Zhang
 * @email zhangpeng@hiynn.com
 * @date: 2019/5/9 17:02
 */
@Api(tags = "spring-boot-web-demo")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final Logger loggerOther = LoggerFactory.getLogger("other");

    @GetMapping("/")
    public String index() {
        loggerOther.info("asdasdasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        log.debug("{}", "input get index...");
        return "this is simple spring boot demo controller";
    }

    @PostMapping("/postA")
    public BaseVO postA(@RequestParam String name) {
        HashMap<String, String> hashMap = new HashMap<String, String>() {
            private static final long serialVersionUID = 4650738796925119538L;

            {
            put("name",name);
        }};
        return BaseVO.success().setData(hashMap);
    }

    @PostMapping("/postB")
    public BaseVO postB(@RequestBody String name,@RequestBody String namea) {
        HashMap<String, String> hashMap = new HashMap<String, String>() {
            private static final long serialVersionUID = -8742217247434516981L;

            {
            put("name",name);
            put("nameA",namea);
        }};
        return BaseVO.success().setData(hashMap);
    }

    @PostMapping("/postC")
    public BaseVO postC(String name) {
        HashMap<String, String> hashMap = new HashMap<String, String>() {
            private static final long serialVersionUID = -5094464811712478321L;

            {
            put("name",name);
        }};
        return BaseVO.success().setData(hashMap);
    }

}
