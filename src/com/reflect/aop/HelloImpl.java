package com.reflect.aop;

/**
 * Created by 枫叶 on 2016/3/14.
 */
public class HelloImpl implements Hello{
    @Override
    public void sayHello(String name) {
        System.out.println(name + ": Hello!");
    }

    @Override
    public void sayBye(String name) {
        System.out.println(name + ": Bye!");
    }
}
