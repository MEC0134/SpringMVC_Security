package com.luv2ocde.springboot.demosecurity.User;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebUser {

    @NotNull(message = "is required")
    @Size(min = 5, message = "is required")
    private String userName;

    @NotNull(message = "is required")
    @Size(min = 8, message = "Must be at least 8 characters")
    private String password;

    private String roleName;


    public WebUser() {

    }

    public WebUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
