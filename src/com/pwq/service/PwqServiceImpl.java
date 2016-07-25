package com.pwq.service;

import org.springframework.stereotype.Service;

@Service
public class PwqServiceImpl implements PwqService{

    private String test;

    PwqServiceImpl() {
        this.test = "pwq";
    }

    public void add() {
        System.out.println("PwqServiceImpl");
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
