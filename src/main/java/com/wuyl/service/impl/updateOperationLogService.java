package com.wuyl.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Service;

@Aspect
@Service
public class updateOperationLogService {
    Logger logger = LogManager.getLogger("自定义");
    private Long currTime1 = System.currentTimeMillis();
    private Long currTime2 = System.currentTimeMillis();

    //@Pointcut("execution(* *(..))")
    @Pointcut("execution(* com.wuyl.service..*.*(..))")
    public void serviceAspect(){

    }

    @Before("serviceAspect()")
    public void doBefore(JoinPoint joinPoint){
        try{
            currTime1 = System.currentTimeMillis();
            logger.info("操作开始"+currTime1);
            //log入表操作

        }catch (Exception e){
            logger.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    @AfterReturning(returning = "result", pointcut = "serviceAspect()")
    public void doAfterReturning(Object result) {
        try{
            currTime2 = System.currentTimeMillis();
            logger.info("操作结束"+currTime2+"耗时："+(currTime2-currTime1)+"毫秒");
        }catch (Exception e){
            logger.error("***操作请求日志记录失败doAfterReturning()***", e);
        }
    }
}
