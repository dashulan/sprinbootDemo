package com.dashulan.demo.service;

import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long userRegister(User user) {
       Long id =  userDao.save(user).getId();
       return id;
    }

    public boolean userLogin(User user) {
        return false;
    }
}
