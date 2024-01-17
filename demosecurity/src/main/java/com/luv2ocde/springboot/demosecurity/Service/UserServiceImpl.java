package com.luv2ocde.springboot.demosecurity.Service;

import com.luv2ocde.springboot.demosecurity.Dao.UserDao;
import com.luv2ocde.springboot.demosecurity.Dao.RoleDao;
import com.luv2ocde.springboot.demosecurity.Entity.Role;
import com.luv2ocde.springboot.demosecurity.Entity.User;
import com.luv2ocde.springboot.demosecurity.User.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleDao.findRoleByName(roleName);
    }

    @Override
    public void save(WebUser webUser) {

        User user = new User();

        // assign web users details to new user object (WebUser has been bind to the registration form)
        user.setUserName(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));


        user.setRoles(Arrays.asList(roleDao.findRoleByName(webUser.getRoleName())));

        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // retrieve user
        User user = userDao.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        // create userdetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    //  method converts a collection of role object to a collection of SimpleGrantedAuthority objects
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
