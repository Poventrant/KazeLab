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
public class GenTypeTest{

    public static void main(String[] args) {
        UserEntity gtt = new UserEntity();
        System.out.println(gtt.getEntityClass());

    }
}
