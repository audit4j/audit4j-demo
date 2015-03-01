package org.audt4j.demo.spring.model;

import java.io.Serializable;

public class User implements Serializable{

    /**
     * asdas
     */
    private static final long serialVersionUID = 1L;

    private long id;
    
    private String username;
    
    private String firstName;
    
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
