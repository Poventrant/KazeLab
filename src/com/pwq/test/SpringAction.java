package com.pwq.test;

public class SpringAction {
    //注入对象
    private StringBuilder staticFactoryDao;

    public void staticFactoryOk() {
        System.out.println(staticFactoryDao.toString());
    }

    //注入对象的set方法  
    public void setStaticFactoryDao(StringBuilder staticFactoryDao) {
        this.staticFactoryDao = staticFactoryDao;
    }
}