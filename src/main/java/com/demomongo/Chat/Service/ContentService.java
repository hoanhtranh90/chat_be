package com.demomongo.Chat.Service;


import com.demomongo.Auth.entity.User;
import com.demomongo.Chat.Modal.*;
import com.demomongo.Chat.Repository.ContentRepository;
import com.demomongo.Chat.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private RoomRepository roomRepository;
    private User user;
    private Room room;
    private Content content;
//    private UserRoom userRoom;
}
