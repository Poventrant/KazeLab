package com.pwq.dao;

import com.pwq.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by kaze on 16-3-19.
 */

@Repository("userRepository")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}