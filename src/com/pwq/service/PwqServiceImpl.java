package com.pwq.service;

/**
 * Created by 枫叶 on 2016/3/20.
 */
public class PwqServiceImpl {

    private String test;

    PwqServiceImpl(String test) {
        this.test = test;
    }

    public void add() {
        System.out.println("KazeServiceImpl0");
    }

    public int sum() {
        return -1;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
