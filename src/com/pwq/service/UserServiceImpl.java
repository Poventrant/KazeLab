package com.pwq.service;

import com.pwq.dao.BaseDao;
import com.pwq.dao.UserDao;
import com.pwq.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 枫叶 on 2016/3/20.
 */
@Service
@Lazy(true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    protected UserDao userDao;
    private int test = 11;

    @Resource
    public void setBaseDao(BaseDao<User> baseDao) {
        super.dao = baseDao;
    }

    public void add() {
       userDao.update(new User(1, "kaze"));
      /* try {
            int i = 4 / 0;  //制造异常，检测回滚事务
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println("UserServiceImpl.add()");
    }

    @Override
    public User get(int userId) {
        return userDao.get(1);
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    public int sum() {
        return -1;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public void queryByProperties(User user) {
        update(user);
    }
}
