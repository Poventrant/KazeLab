package com.pwq;

import com.pwq.service.AspectTest;
import com.pwq.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
						new String[]{"applicationContext.xml"} );
		log.info("Going to create HelloWord Obj");
		/*UserController userController = (UserController) appContext.getBean("userController");
        userController.getUserService().add();*/

        UserService userService  = (UserService) appContext.getBean("userServiceImpl");

        userService.add();
        userService.add();
        System.out.println(userService.sum());

        AspectTest ast = new AspectTest();
        ast.test();

        ast = (AspectTest)appContext.getBean("aspectTest");
        ast.test();


//		appContext.getBean("kazeServiceImpl0");

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