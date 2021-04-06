package com.building.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * 环绕通知
 *
 * @author yinjiahui
 * @create 2021-04-05 11:26
 */
@Aspect
@Component
@Slf4j
public class LogbackAspect {
    // 我们的需求是做 入参、出参日志

    /**
     * logback环绕通知
     *
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.building.controller.*.*(..))")
    public Object logback(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("----------------------开始执行" +joinPoint.getTarget()+"的"+ joinPoint.getSignature().getName() + "方法---------------------------------------------------------------------------------");

        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            log.info("请求参数" + (i + 1) + "的值为:" + joinPoint.getArgs()[i]);
        }

        Object obj = joinPoint.proceed();// 执行控制器方法

        log.info("返回值" + obj);

        log.info("----------------------" + joinPoint.getSignature().getName() + "执行完毕,消耗时间"+(System.currentTimeMillis() - start)+"ms---------------------------------------------------------------------------------");

        return obj;
    }
}
