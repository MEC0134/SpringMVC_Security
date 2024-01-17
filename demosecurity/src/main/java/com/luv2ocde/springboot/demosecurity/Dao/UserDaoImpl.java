package com.luv2ocde.springboot.demosecurity.Dao;

import com.luv2ocde.springboot.demosecurity.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {


    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String userName) {

        // retrieve user based on username
        TypedQuery<User> user = entityManager.createQuery("FROM User WHERE userName=:uName", User.class);
        user.setParameter("uName", userName);

        User theUser = null;

            try {
                theUser = user.getSingleResult();
            } catch (Exception e) {
                theUser = null;
            }

        return theUser;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }
}
