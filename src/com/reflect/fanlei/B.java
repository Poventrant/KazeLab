package com.reflect.fanlei;

public class B extends A<User>{

    public static void main(String[] args) {
        System.out.println(new B().getEntityClass());
    }

}
