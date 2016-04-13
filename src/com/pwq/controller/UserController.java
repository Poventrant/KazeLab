package com.pwq.controller;

import com.pwq.entity.User;
import com.pwq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by kaze on 16-3-19.
 */
@Controller("userController")
public class UserController {
    @Resource(name="userServiceImpl")
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
