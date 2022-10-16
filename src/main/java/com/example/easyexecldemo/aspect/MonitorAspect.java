package com.example.easyexecldemo.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class MonitorAspect {

    // 修正Timer注解的全局唯一限定符
    @Pointcut("@annotation(com.example.easyexecldemo.annotation.TimeMonitor)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标Logger

        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();

        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        log.info("{}: {}: start...", clazzName, methodName);

        String targetMethodParams = Arrays.toString(joinPoint.getArgs());
        // 调用目标方法
        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - start;
        log.info("{}: {}: {}: end... cost time: {} ms", clazzName, methodName, targetMethodParams, time);

        return result;
    }
}
