package com.demomongo.Chat.Service;

import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.repository.UserRepository;
import com.demomongo.Auth.service.UserService;
import com.demomongo.Auth.service.impl.UserServiceImpl;
import com.demomongo.Chat.Modal.*;
import com.demomongo.Chat.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CreateService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserServiceImpl userService;

    private User user;
    private Room room;
    private Content content;




}
