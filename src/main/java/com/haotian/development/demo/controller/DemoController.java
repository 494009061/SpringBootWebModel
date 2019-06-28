package com.haotian.development.demo.controller;

import com.haotian.core.util.UniqueIdUtils;
import com.haotian.development.demo.service.DemoService;
import com.haotian.development.entity.DemoTest;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DemoService demoService;

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private  final Logger loggerOther = LoggerFactory.getLogger("other");
    @GetMapping("/")
    public String index() {
        loggerOther.info("asdasdasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        log.debug("{}", "input get index...");
        return "this is simple spring boot demo controller";
    }

    @GetMapping("/insert")
    public int insert() {
        DemoTest entity = new DemoTest();
        entity.setId(UniqueIdUtils.getSortNumber());
        return demoService.insert(UniqueIdUtils.getSortNumber(),entity);
    }

}
