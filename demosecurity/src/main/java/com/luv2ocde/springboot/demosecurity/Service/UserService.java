package com.luv2ocde.springboot.demosecurity.Service;

import com.luv2ocde.springboot.demosecurity.Entity.Role;
import com.luv2ocde.springboot.demosecurity.Entity.User;
import com.luv2ocde.springboot.demosecurity.User.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    void save(WebUser webUser);

    Role findByRoleName(String roleName);

}
