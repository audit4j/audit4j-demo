package org.audt4j.demo.hibernate.dao;

import org.audt4j.demo.hibernate.model.User;

public interface UserDao {

    User getUserByUserName(String userName);
    
    User addUser(User user);

}
