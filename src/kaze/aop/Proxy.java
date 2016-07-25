package kaze.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by kaze on 2016/7/25.
 */
public class Proxy implements InvocationHandler {

    private Object target;

    public Object bind(Class clazz) {
        try {
            return bind(clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object bind(Object proxy) {
        this.target = proxy;
        Class clazz = proxy.getClass();
        return java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object o = method.invoke(this.target, args);
        System.out.println("after");
//        if(proxy instanceof Kaze) {
//            ((Kaze) proxy).say();
//        }
        return o;
    }
}
