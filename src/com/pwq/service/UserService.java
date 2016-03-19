package com.pwq.service;

import com.pwq.dao.BaseDao;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kaze on 16-3-19.
 */
@Service("userService")
public class UserService extends BaseService<User>{
    @Autowired
    protected UserDao userDao;

    @Resource
    public void setBaseDao(BaseDao<User> baseDao) {
        super.setBaseDao(baseDao);
    }

}
