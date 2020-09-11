package com.wuyl.service.impl;


import com.wuyl.service.thread.OperationLogThread;
import com.wuyl.utils.SpringContextUtils;
import com.wuyl.vo.OperationLogVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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

    @AfterThrowing(throwing = "e",pointcut = "serviceAspect()")
    public void doAfterThrowing(Throwable e){
        currTime2 = System.currentTimeMillis();
        logger.error("service层发生错误，aop探查异常",e);

        OperationLogVO operationLogVO = new OperationLogVO();
        operationLogVO.setResult(e.getMessage());
        operationLogVO.setRequestStartTime(currTime1);
        operationLogVO.setRequestEndTime(currTime2);
        OperationLogThread operationLogThread = new OperationLogThread(operationLogVO);
        ThreadPoolTaskExecutor poolTaskExecutor = (ThreadPoolTaskExecutor)SpringContextUtils.getBean("operationLogThreadPool");
        poolTaskExecutor.execute(operationLogThread);
        //ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //cachedThreadPool.execute(operationLogThread);
        //operationLogThread.start();
    }
}
