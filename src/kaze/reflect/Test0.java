package kaze.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test0
{
    // 这样做必须在类中有一个空构造方法
    public static void main(String[] args) {
        Class<?> c = null;
        try {
            c = Class.forName("kaze.reflect.Person0");
            Constructor<?> con = c.getConstructor(String.class, int.class);
            Person0 p = (Person0) con.newInstance("xy", 20);
            System.out.println(p);

            Method m = c.getMethod("add");
            m.invoke(p);

            Method m0 = c.getMethod("addWithParameters", String.class, int.class);
            m0.invoke(p, "haha", 20);

            setter(p, "name", "xy", String.class);
            setter(p, "age", 29, int.class);
            getter(p, "age");
            getter(p, "name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj：要操作的对象
     * @param att：要操作的属性
     * @param value：要设置的属性内容
     * @param type：要设置的属性类型
     */
     public static void setter(Object obj, String att, Object value, Class<?> type) {
         try {
             // 得到setter方法
             Method m = obj.getClass().getMethod("set" + initStr(att), type);
             m.invoke(obj, value);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    /**
     * @param obj：要操作的对象
     * @param att：要操作的属性
     */
    public static void getter(Object obj, String att) {
        try {
            // 得到getter方法
            Method m = obj.getClass().getMethod("get" + initStr(att));
            System.out.println(m.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String initStr(String oldStr) {
        String newStr = oldStr.substring(0, 1).toUpperCase() + oldStr.substring(1);
        return newStr;
    }

}