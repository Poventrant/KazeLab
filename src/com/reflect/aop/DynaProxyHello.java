package com.reflect.aop;

import sun.security.jgss.spi.MechanismFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 枫叶 on 2016/3/14.
 */
public class DynaProxyHello implements InvocationHandler{

    private Object delegate;
    private Object logger;

    public Object bind(Object o, Object logger) {
        this.delegate = o;
        this.logger = logger;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
                this.delegate.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = null;
        try {
            Class clazz = this.logger.getClass();
            Method start = clazz.getDeclaredMethod("start");
            start.invoke(this.logger);
//            System.out.println("before");
            o = method.invoke(this.delegate, args);
//            System.out.println("after");
            Method end = clazz.getDeclaredMethod("end");
            end.invoke(this.logger);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
