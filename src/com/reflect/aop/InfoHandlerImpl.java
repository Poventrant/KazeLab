package com.reflect.aop;

/**
 * Created by 枫叶 on 2016/3/14.
 */
public class InfoHandlerImpl implements InfoHandler{

    @Override
    public void start() {
        System.out.println("Method start!");
    }

    @Override
    public void end() {
        System.out.println("Method end!");
    }
}
