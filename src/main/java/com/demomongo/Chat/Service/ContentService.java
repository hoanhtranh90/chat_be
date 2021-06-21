package com.demomongo.Chat.Service;


import com.demomongo.Auth.entity.User;
import com.demomongo.Chat.Modal.*;
import com.demomongo.Chat.Repository.ContentRepository;
import com.demomongo.Chat.Repository.RoomRepository;
import com.demomongo.Chat.Repository.UserRoomContentRepository;
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
    @Autowired
    private UserRoomContentRepository userRoomContentRepository;
    private User user;
    private Room room;
    private Content content;
//    private UserRoom userRoom;
    private UserRoomContent userRoomContent;




    public List<Content> findByRoom(String roomName, Pageable pageable){
//        Room room = roomRepository.findByName(roomName);

//        List<Content> mainList1 = new ArrayList<Content>();
//        mainList1.addAll(room.getItems());

        return null;
    }
}
