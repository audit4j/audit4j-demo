package org.audt4j.demo.spring.service;

import org.audit4j.core.annotation.IgnoreAudit;
import org.audt4j.demo.spring.model.User;


public interface UserService {

    @IgnoreAudit
    void saveUser(User user);

    User getUserByuserName(String userName);
}
