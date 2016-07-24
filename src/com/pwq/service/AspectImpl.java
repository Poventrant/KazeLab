package com.pwq.service;

import org.springframework.stereotype.Service;

/**
 * Created by 枫叶 on 2016/4/5.
 */
@Service
public class AspectImpl implements Aspect {
    public void test() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
