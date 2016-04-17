package com.pwq.test;

public class StaticDaoFactory {
    //静态工厂  
    public static final StringBuilder getStaticFactoryDaoImpl() {
        return new StringBuilder("static inject");
    }
}