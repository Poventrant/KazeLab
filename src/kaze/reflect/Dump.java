package kaze.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by 枫叶 on 2016/3/23.
 */
public class Dump {

    private int i;

    private void say() {
        System.out.println("say");
    }

    public static void main(String[] args) {
        Class<Dump> clazz = Dump.class;
        try {
            Object o = clazz.newInstance();
            Method m = clazz.getDeclaredMethod("say");
            if(!m.isAccessible()) m.setAccessible(true);
            m.invoke(o);
            Field f = clazz.getDeclaredField("i");
            if(!f.isAccessible()) f.setAccessible(true);
            f.set(o, 23);
            System.out.println(f.get(o));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
