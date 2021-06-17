package com.demomongo.Auth.service;


import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.security.UserPrincipal;

public interface UserService {
    User createUser(User user);

    UserPrincipal findByUsername(String username);
}
