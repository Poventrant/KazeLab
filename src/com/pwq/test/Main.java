package com.pwq.test;

import com.pwq.controller.UserController;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
						new String[]{"applicationContext.xml"} );

		UserController userController = (UserController) appContext.getBean("userController");
		System.out.println(userController.getUserService().findById(0));

		/*UserDao userDao = (UserDao) appContext.getBean("userRepository");
		System.out.println(userDao.getSession());*/

	}
}