package com.pwq.aspect;

import com.pwq.service.UserServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by 枫叶 on 2016/3/20.
 */
@Component("userServiceImplAspect")
@Aspect
public class UserServiceImplAspect {

    @Around("execution(* com.pwq.service.PwqServiceImpl.*(..))")
    public Object check(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before check");
        Object result = null;
        /*Object us = proceedingJoinPoint.getTarget();
        Class clazz = proceedingJoinPoint.getTarget().getClass();
        System.out.println(clazz);
        try {
            Method method0 = clazz.getMethod("setTest", int.class),
                    method1 = clazz.getMethod("getTest");
            int temp =  (int)method1.invoke(us);
            System.out.println((int)method1.invoke(us));
            method0.invoke(us, temp + 1);
            System.out.println((int)method1.invoke(us));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        UserServiceImpl usi = (UserServiceImpl) proceedingJoinPoint.getTarget();
        System.out.println(usi.getTest());
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after check");
        return result;
    }

    @Around("execution(* com.pwq.service.AspectTest.*(..))")
    public Object check0(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before AspectTest check");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after AspectTest check");
        return result;
    }

}
