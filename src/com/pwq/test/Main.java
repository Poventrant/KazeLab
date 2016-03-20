package com.pwq.test;

import com.pwq.controller.UserController;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import com.pwq.service.KazeServiceImpl0;
import com.pwq.service.UserService;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
						new String[]{"applicationContext.xml"} );

		UserController userController = (UserController) appContext.getBean("userController");
        userController.getUserService().add();

        System.out.println( (appContext.getBean("kazeServiceImpl0") instanceof UserService) );

        /*String[] beans = appContext.getBeanDefinitionNames();
        for(String s : beans) {
            System.out.println(s);
        }
        UserService userService = (UserService)appContext.getBean("userServiceImpl");
        User user = (User)userService.findById(1);
        System.out.println(user.getName());*/

		/*System.out.println(userController.getUserService().findById(1).getName());
        User user = new User();*/
        /*user.setName( String.valueOf( System.currentTimeMillis() ) );
        userController.getUserService().add(user);
        System.out.println(userController.getUserService().findById(1).getName());*/
		/*UserDao userDao = (UserDao) appContext.getBean("userRepository");
		System.out.println(userDao.getSession());*/

	}
}