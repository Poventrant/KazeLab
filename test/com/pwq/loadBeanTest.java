package com.pwq;

import com.pwq.entity.User;
import com.pwq.service.PwqServiceImpl;
import com.pwq.service.UserService;
import com.pwq.test.SpringAction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 枫叶 on 2016/4/17.
 */
public class loadBeanTest {
    @Test
    public void loadBean() {

        ApplicationContext appContext = new ClassPathXmlApplicationContext( new String[]{"applicationContext.xml"} );

        UserService userService  = (UserService) appContext.getBean("userServiceImpl");

        //测试事务：query开头函数是否真的是readOnly
        userService.queryByProperties(new User(1, "kaze"));

        UserService kazeServiceImpl0  = (UserService) appContext.getBean("kazeServiceImpl");

        System.out.println(userService == kazeServiceImpl0);

        PwqServiceImpl pwqService  = (PwqServiceImpl) appContext.getBean("pwqService");

        PwqServiceImpl pwqService0  = (PwqServiceImpl) appContext.getBean("pwqService");

        System.out.println(pwqService.getTest());

        System.out.println(pwqService == pwqService0);

        SpringAction springAction = (SpringAction) appContext.getBean("springAction");

        springAction.staticFactoryOk();

    }
}
