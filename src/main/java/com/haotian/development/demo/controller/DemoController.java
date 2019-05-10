package com.haotian.development.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * simple demo controller
 * @author: Mr.Zhang
 * @email zhangpeng@hiynn.com
 * @date: 2019/5/9 17:02
 */
@Api(tags = "Demo API")
@RestController
@RequestMapping(value = "demo", params = {"", ""})
public class DemoController {


}
