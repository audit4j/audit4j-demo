package org.audt4j.demo.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.DeIdentify;
import org.audit4j.core.annotation.IgnoreAudit;

/**
 * The Class User.
 * 
 * @author <a href="mailto:janith3000@gmail.com">Janith Bandara</a>
 * 
 */
@Entity
@Table(name = "user")
@Audit
public class User implements Serializable {

    /** asdas. */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    /** The username. */
    @Column(name = "user_name")
    private String username;

    /** The first name. */
    @Column(name = "first_name")
    private String firstName;

    /** The last name. */
    @Column(name = "last_name")
    private String lastName;

    /** The password. */
    @Column(name = "password")
    private String password;

    /**
     * Instantiates a new user.
     * 
     * @param userName
     *            the user name
     */
    public User(String userName) {
        this.username = userName;
    }

    public User(String userName, String password) {
        this(userName);
        this.password = password;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username
     *            the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name.
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName
     *            the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName
     *            the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password
     *            the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
