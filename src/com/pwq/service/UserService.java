package com.pwq.service;

import com.pwq.entity.User;

public interface UserService extends BaseService<User>{
    void add();

    User get(int userId);

    void save(User user);

    int  sum();

    public void queryByProperties(User user);
}
