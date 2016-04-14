package kaze.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 枫叶 on 2016/4/14.
 */
public class MainRunTest {
    public static void main(String[] args) {
        try {
            Class<Test> clazz = Test.class;
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("main", String[].class);
            String[] params = null;
            method.invoke(object, (Object) params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
