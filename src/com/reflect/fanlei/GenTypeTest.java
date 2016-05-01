package com.reflect.fanlei;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by uc on 2016/3/21.
 */
public class GenTypeTest extends Entity<User>{
    public static void main(String[] args) {
        GenTypeTest gtt = new GenTypeTest();
        System.out.println(gtt.getEntityClass());

        try {
            Field field = String.class.getDeclaredField("hash");
            Type type = field.getGenericType();
//            String typeName = ((Class) type).getTypeName();
//            System.out.println(typeName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
