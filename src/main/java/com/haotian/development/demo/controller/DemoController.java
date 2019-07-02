package com.haotian.development.demo.controller;

import com.haotian.core.vo.BaseVO;
import com.haotian.development.demo.service.DemoService;
import com.haotian.development.entity.DemoTest;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

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

    @GetMapping("/insert")
    public BaseVO insert(@Validated({Insert.class}) DemoTest demoTest) {
        HashMap<String, String> hashMap = new HashMap<String, String>() {{
            put("a", "a");
            put("a1", "a1");
            put("a2", "a2");

        }};
        return BaseVO.build().success().setData(hashMap);
    }


    @GetMapping("/update")
    public BaseVO update(@Validated({Update.class}) DemoTest demoTest) {
        HashMap<String, String> hashMap = new HashMap<String, String>() {{
            put("a", "a");
            put("a1", "a1");
            put("a2", "a2");

        }};

        return BaseVO.build().success().setData(hashMap);
    }

}
