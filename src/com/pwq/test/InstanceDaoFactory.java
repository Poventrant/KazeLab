package com.pwq.test;

public class InstanceDaoFactory {
    //静态工厂  
    public StringBuilder getFactoryDaoImpl() {
        return new StringBuilder("instance inject");
    }
}