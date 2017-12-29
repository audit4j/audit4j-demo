package org.audt4j.demo.hibernate.service;

import org.audt4j.demo.hibernate.model.User;

public interface UserService {

    
    void saveUser(User user);

    User getUserByuserName(String userName);

    void login(String userName, String password);

    void changePassword(String oldPassword, String newPassword);
}
