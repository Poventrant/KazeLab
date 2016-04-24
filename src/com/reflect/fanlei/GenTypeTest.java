package com.reflect.fanlei;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
            String typeName = ((Class) type).getTypeName();
            System.out.println(typeName);
            Map<String, Object> map = new HashMap<String, Object>();
            System.out.println(Arrays.toString( map.getClass().getTypeParameters() ));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
