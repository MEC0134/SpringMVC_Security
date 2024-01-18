package com.luv2ocde.springboot.demosecurity.Controller;

import com.luv2ocde.springboot.demosecurity.Entity.Role;
import com.luv2ocde.springboot.demosecurity.Entity.User;
import com.luv2ocde.springboot.demosecurity.Service.UserService;
import com.luv2ocde.springboot.demosecurity.User.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RegisterController {

    private Logger logger = Logger.getLogger(getClass().getName());
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody WebUser theWebUser) {

        String userName = theWebUser.getUserName();
        logger.info("Processing registration for: " + userName);

        // check db if user exists
        User user = userService.findByUserName(userName);
        if(user != null) {
            String errorMsg = "User name already exists.";
            logger.warning(errorMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }


        // check db if role is valid
        String roleName = theWebUser.getRoleName();
        Role role = userService.findByRoleName(roleName);
        if(role == null) {
            String errorMsg = "Invalid Role";
            logger.warning(errorMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }

        userService.save(theWebUser);

        return ResponseEntity.ok().build();
    }





}
