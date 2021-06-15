package com.demomongo.Service;

import com.demomongo.Modal.Content;
import com.demomongo.Modal.ContentDao;
import com.demomongo.Modal.Room;
import com.demomongo.Repository.ContentRepository;
import com.demomongo.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private RoomRepository roomRepository;

    public void saveRoom(Room room){
        if(!roomRepository.existsRoomByName(room.getName()))
        {roomRepository.save(room);}
    }
}
