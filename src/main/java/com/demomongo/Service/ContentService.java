package com.demomongo.Service;


import com.demomongo.Modal.*;
import com.demomongo.Repository.ContentRepository;
import com.demomongo.Repository.RoomRepository;
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
    private UserRoom userRoom;
    private UserRoomContent userRoomContent;




    public List<Content> findByRoom(String roomName, Pageable pageable){
//        Room room = roomRepository.findByName(roomName);
//        List<Content> mainList1 = contentRepository.findAllByRoomId(room.getId(),pageable);

//        List<Content> mainList1 = new ArrayList<Content>();
//        mainList1.addAll(room.getItems());

        return null;
    }
}
