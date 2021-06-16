package com.demomongo.Service;

import com.demomongo.Modal.Content;
import com.demomongo.Modal.Room;
import com.demomongo.Modal.UserRoomContent;
import com.demomongo.Repository.ContentRepository;
import com.demomongo.Repository.DAO.ContentDAOquery;
import com.demomongo.Repository.RoomRepository;
import com.demomongo.Repository.UserRoomContentRepository;
import com.demomongo.Repository.UserRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRoomRepository userRoomRepository;
    @Autowired
    private UserRoomContentRepository userRoomContentRepository;

    public void saveRoom(String userName, String roomName){
//        if( !userRoomRepository.existsById(room.getName()) || !)
//        {roomRepository.save(room);}
    }
    public List<UserRoomContent> loadHistory(String roomName){
        List<UserRoomContent> list = userRoomContentRepository.findAllByRoomName(roomName);
        return list;
    }
}
