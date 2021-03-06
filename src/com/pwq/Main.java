package com.pwq;

import com.pwq.controller.UserController;
import com.pwq.entity.User;
import com.pwq.service.PwqService;
import com.pwq.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        PwqService ast = (PwqService) appContext.getBean("pwqServiceImpl");
        ast.add();

        UserController userController = (UserController) appContext.getBean("userController");
        UserService userService = userController.getUserService();
        User user = userService.get(1);
        System.out.println(user.getName());
        userService.add();

        appContext.close();
//        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		  UserController userController = (UserController) appContext.getBean("userController");
//        userController.getUserService().add();*/
//
//        UserService userService = (UserService) appContext.getBean("userServiceImpl");
//
//        userService.add();
//        userService.add();

//        System.out.println(userService.sum());
//
//        AspectTest ast = new AspectTest();
//        ast.test();
//
//        ast = (AspectTest) appContext.getBean("aspectTest");
//        ast.test();


//		appContext.getBean("kazeServiceImpl0");
//
//        String[] beans = appContext.getBeanDefinitionNames();
//        for(String s : beans) {
//            System.out.println(s);
//        }
//        UserService userService = (UserService)appContext.getBean("userServiceImpl");
//        User user = (User)userService.findById(1);
//        System.out.println(user.getName());
//
//		System.out.println(userController.getUserService().findById(1).getName());
//        User user = new User();
//        user.setName( String.valueOf( System.currentTimeMillis() ) );
//        userController.getUserService().add(user);
//        System.out.println(userController.getUserService().findById(1).getName());
//		UserDao userDao = (UserDao) appContext.getBean("userRepository");
//		System.out.println(userDao.getSession());

    }
}