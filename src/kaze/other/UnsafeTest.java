package kaze.other;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by kaze on 2016/7/3.
 */
public class UnsafeTest {
    public static void main(String[] args) {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        try {
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            unsafe.allocateMemory(100);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
