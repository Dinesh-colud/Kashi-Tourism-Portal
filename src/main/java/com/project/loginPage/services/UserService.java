package com.project.loginPage.services;

import com.project.loginPage.entities.User;

public interface UserService {
    public boolean userRegister(User user);
    public User loginUser(String email,String password);
}
