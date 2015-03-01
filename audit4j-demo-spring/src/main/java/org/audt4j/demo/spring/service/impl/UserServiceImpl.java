package org.audt4j.demo.spring.service.impl;

import org.audt4j.demo.spring.model.User;
import org.audt4j.demo.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements UserService{

    @Override
    public void saveUser(User user){
        
    }
    
    @Override
    public User getUserByuserName(String userName){
        return new User();
    }
}
