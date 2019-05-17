package com.haotian.development.demo.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/")
    public String index(){
        log.debug("{}","input get index...");
        return "this is simple spring boot demo controller";
    }


}
