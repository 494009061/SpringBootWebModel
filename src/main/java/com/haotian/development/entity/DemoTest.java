package com.haotian.development.entity;

import org.apache.ibatis.type.Alias;

/**
 * @author: Mr.Zhang
 * @email zhangpeng@hiynn.com
 * @date: 2019/6/10 14:12
 */
@Alias("DemoTestEntity")
public class DemoTest {

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
