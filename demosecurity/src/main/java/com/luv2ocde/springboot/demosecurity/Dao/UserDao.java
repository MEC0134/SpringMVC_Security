package com.luv2ocde.springboot.demosecurity.Dao;


import com.luv2ocde.springboot.demosecurity.Entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);

}
