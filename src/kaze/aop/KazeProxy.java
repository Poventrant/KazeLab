package kaze.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by kaze on 16-4-24.
 */
public class KazeProxy implements IKazeProxy{
    KazeProxy() {
        System.out.println("Loading KazeProxy");
    }
    public static void main(String[] args) {
        IKazeProxy kazeProxy = (IKazeProxy) new ObjectProxy().getProxy(KazeProxy.class);
        kazeProxy.say();
        System.out.println(kazeProxy instanceof InvocationHandler);
        try {
            Class clazz = KazeProxy.class;
            clazz = Class.forName("kaze.aop.KazeProxy");
            System.out.println(clazz.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(Son.class.getSimpleName());
        Son son = new Son();
        System.out.println(son.getClass().getSuperclass().getSimpleName());
        System.out.println(son instanceof Father);
        System.out.println(Father.class.isInstance(son));
    }

    public void say() {
        System.out.println("KazeProxy");
    }

    static class Father {}
    static class Son extends Father {}

    static class ObjectProxy implements InvocationHandler{
        private Object target;
        private Object proxy;

        public Object getProxy(Class clazz) {
            try {
                this.target = clazz.newInstance();
                return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object o = null;
            try {
                System.out.println("before");
                o = method.invoke(this.target, args);
                System.out.println("after");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return o;
        }
    }
}
