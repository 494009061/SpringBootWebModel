package com.haotian.development.entity;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

/**
 * @author: Mr.Zhang
 * @email zhangpeng@hiynn.com
 * @date: 2019/6/10 14:12
 */
@Alias("DemoTestEntity")
public class DemoTest {
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
