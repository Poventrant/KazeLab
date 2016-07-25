package com.pwq.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by 枫叶 on 2016/4/5.
 */
@Component
@Aspect
@SuppressWarnings("all")
public class AspectProxy {

    @Around("execution(* com.pwq.service.PwqServiceImpl.*(..))")
    public Object check(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before check");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after check");
        return result;
    }
}
