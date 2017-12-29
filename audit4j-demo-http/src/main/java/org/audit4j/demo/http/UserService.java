package org.audit4j.demo.http;

import org.audit4j.core.annotation.Audit;

@Audit
public class UserService {

    
    public boolean login(final String userName, final String password){
        if (userName.equals("test") && password.equals("123")) {
            return true;
        }
        return false;
    }
}
