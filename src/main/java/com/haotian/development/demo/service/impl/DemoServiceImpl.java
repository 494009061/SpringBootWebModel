package com.haotian.development.demo.service.impl;

import com.haotian.core.aop.annotation.SysLogByInsert;
import com.haotian.development.demo.dao.DemoTestMapper;
import com.haotian.development.demo.service.DemoService;
import com.haotian.development.entity.DemoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.Zhang
 * @email zhangpeng@hiynn.com
 * @date: 2019/6/10 14:11
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoTestMapper demoTestMapper;

    @Override
    @SysLogByInsert
    public int insert(String id, DemoTest entity) {
        entity.setId(id);
        return demoTestMapper.insertDemoTest(entity);
    }
}
