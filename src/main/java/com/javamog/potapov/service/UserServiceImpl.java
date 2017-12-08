package com.javamog.potapov.service;

import com.javamog.potapov.dao.UserDao;
import com.javamog.potapov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void saveUser(User user) {

        userDao.saveUser(user);

    }

    @Override
    public User getUser(){
        return userDao.getUser();
    }

    @Override
    public User getUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
