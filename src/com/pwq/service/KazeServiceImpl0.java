package com.pwq.service;

import com.pwq.dao.BaseDao;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 枫叶 on 2016/3/20.
 */
@Service
@Lazy(true)
public class KazeServiceImpl0 extends BaseServiceImpl<User> implements UserService {

    @Autowired
    protected UserDao userDao;

    @Resource
    public void setBaseDao(BaseDao<User> baseDao) {
        super.dao = baseDao;
    }

    public void add() {
        System.out.println("KazeServiceImpl0");
    }

    public int sum() {
        return -1;
    }
}
