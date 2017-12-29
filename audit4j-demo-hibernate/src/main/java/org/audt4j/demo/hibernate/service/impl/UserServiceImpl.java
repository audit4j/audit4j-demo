package org.audt4j.demo.hibernate.service.impl;

import javax.transaction.Transactional;

import org.audit4j.core.annotation.IgnoreAudit;
import org.audt4j.demo.hibernate.dao.UserDao;
import org.audt4j.demo.hibernate.model.User;
import org.audt4j.demo.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void addUser() {

    }

    @Override
    @Transactional
    public void login(String userName, String password) {
        User user = userDao.getUserByUserName(userName);

    }

    @Override
    public User getUserByuserName(String userName) {
        return new User("admin");
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.addUser(user);

    }

    @Override
    @IgnoreAudit
    public void changePassword(String oldPassword, String newPassword) {
        // Method Body

    }

}
