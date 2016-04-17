package com.pwq.service;

import com.pwq.dao.BaseDao;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kaze on 16-3-19.
 */
public interface UserService extends BaseService<User>{
    void add();

    int  sum();

    public void queryByProperties(User user);
}
