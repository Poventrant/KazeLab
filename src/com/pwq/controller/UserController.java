package com.pwq.controller;

import com.pwq.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by kaze on 16-3-19.
 */
@Controller("userController")
public class UserController implements BeanPostProcessor, InitializingBean, ApplicationListener {

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource(name="userServiceImpl")
    private UserService userService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before precess");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after precess");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public void initIt() {
        System.out.println("init method");
    }

    public void cleanUp() {
        System.out.println("destroy method");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("on app");
    }
}
