package com.example.back2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLogAspect {

    /**
     * 计时
     *
     * @param proceedingJoinPoint 运行态连接点
     */
    @Around("logPoint()")
    private Object timeStart(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();  // 秒表
        stopWatch.start(
                proceedingJoinPoint.getSignature().getDeclaringTypeName() + '.'
                        + proceedingJoinPoint.getSignature().getName()
        );  // 开始计时
        Object res = proceedingJoinPoint.proceed(); // 运行
        stopWatch.stop();   // 掐表
        logger.warn(stopWatch.prettyPrint());   // 打印日志
        return res;
    }

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger("tl");

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.example.back2.controller..*.*.*(..))")
    private void logPoint() {
    }


}
