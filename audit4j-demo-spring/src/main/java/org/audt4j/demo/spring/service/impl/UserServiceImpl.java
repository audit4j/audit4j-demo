package org.audt4j.demo.spring.service.impl;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.DeIdentify;
import org.audit4j.core.annotation.IgnoreAudit;
import org.audt4j.demo.spring.model.User;
import org.audt4j.demo.spring.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
@Audit
public class UserServiceImpl implements UserService {

    @Override
    public void login(String userName, @DeIdentify String password) {
        // Method Body

    }

    @Override
    public User getUserByuserName(String userName) {
        return new User("admin");
    }

    @Override
    public void saveUser(User user) {
        // Method Body

    }

    @Override
    @IgnoreAudit
    public void changePassword(String oldPassword, String newPassword) {
        // Method Body

    }

}
