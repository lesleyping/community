package com.lxp.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AlphaAspect {
    // 定义切点
    @Pointcut("execution(* com.lxp.community.service.*.*(..))")
    public void pointcut() {

    }

    // 通知 advice
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    // 前后都织入
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 目标组件前xxx
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        // 目标组件后xxx
        System.out.println("around after");
        return obj;
    }
}
