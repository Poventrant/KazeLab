package com.reflect.aop;

/**
 * Created by 枫叶 on 2016/3/14.
 */
public class Test {
    public static void main(String[] args) {
        Hello hello = (Hello) new DynaProxyHello().bind(new HelloImpl(), new InfoHandlerImpl());
        hello.sayHello("kaze");
        hello.sayBye("po");
    }
}
