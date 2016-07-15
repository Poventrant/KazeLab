package com.pwq.controller;

import com.pwq.service.PwqService;
import com.pwq.service.PwqServiceImpl;
import com.pwq.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by kaze on 16-3-19.
 */
@Controller("userController")
public class UserController {
    @Resource
    private PwqService pwqService;

}
