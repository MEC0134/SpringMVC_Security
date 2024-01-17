package com.luv2ocde.springboot.demosecurity.Dao;


import com.luv2ocde.springboot.demosecurity.Entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
