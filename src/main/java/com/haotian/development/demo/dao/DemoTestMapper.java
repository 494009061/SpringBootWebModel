package com.haotian.development.demo.dao;

import com.haotian.development.entity.DemoTest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Mr.Zhang
 * @email zhangpeng@hiynn.com
 * @date: 2019/6/6 11:32
 */
@Mapper
public interface DemoTestMapper {

    int insertDemoTest(DemoTest entity);

}
