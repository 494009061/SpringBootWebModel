package com.haotian.core.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Aspect
@Component
public class SysLogAop {

    private final Logger logger = LoggerFactory.getLogger(SysLogAop.class);

    @Around("@annotation(com.haotian.core.aop.annotation.SysLogByInsert)")
    public int aopForInsert(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        logger.info("{}{}","aop for insert in",JSON.toJSONString(args));
        return (int) point.proceed(args);
    }
    @Around("@annotation(com.haotian.core.aop.annotation.SysLogByDelete)")
    public void aopForDelete(ProceedingJoinPoint point) throws Throwable {
        logger.info("{}","aop for delete in ");
        point.proceed();
        logger.info("{}","aop for delete out ");
    }
    @Around("@annotation(com.haotian.core.aop.annotation.SysLogByUpdate)")
    public void aopForUpdate(ProceedingJoinPoint point) throws Throwable {
        System.out.println("aop for update in ");
        point.proceed();
        System.out.println("aop for update out ");
    }





}
