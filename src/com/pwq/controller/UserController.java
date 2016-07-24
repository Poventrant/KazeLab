package com.pwq.controller;

import com.pwq.service.PwqService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by kaze on 16-3-19.
 */
@Controller("userController")
public class UserController implements BeanPostProcessor {
    @Resource
    private PwqService pwqService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before precess" + pwqService);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after precess" + pwqService);
        return bean;
    }
}
