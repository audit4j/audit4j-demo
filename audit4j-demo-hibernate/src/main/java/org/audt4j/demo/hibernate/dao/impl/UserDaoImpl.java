package org.audt4j.demo.hibernate.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.audt4j.demo.hibernate.dao.UserDao;
import org.audt4j.demo.hibernate.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:userName");
        query.setParameter("userName", userName);
        query.setMaxResults(1);

        List<User> users = query.getResultList();
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }
}
