package com.demomongo.Auth.service;


import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.security.UserPrincipal;
import com.demomongo.Chat.Modal.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User createUser(User user);

    UserPrincipal findByUsername(String username);

    List<Room> roomOfUser();

    ResponseEntity<?> login(User user);
}
