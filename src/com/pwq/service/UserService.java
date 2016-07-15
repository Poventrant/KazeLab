package com.pwq.service;

import com.pwq.dao.BaseDao;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface UserService extends BaseService<User>{
    void add();

    User get(int userId);

    void save(User user);

    int  sum();

    public void queryByProperties(User user);
}
