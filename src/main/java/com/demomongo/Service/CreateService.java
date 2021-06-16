package com.demomongo.Service;

import com.demomongo.Modal.*;
import com.demomongo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private RoomRepository roomRepository;
//    @Autowired
//    private UserRoomRepository userRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoomContentRepository userRoomContentRepository;
    @Autowired
    private RoomService roomService;

    private User user;
    private Room room;
    private Content content;
//    private UserRoom userRoom;
    private UserRoomContent userRoomContent;


//    public Boolean createUser(String userName){
//        if(userRepository.existsByName(userName)) {
//            user = userRepository.findByName(userName);
//            return false;
//        }
//        else {
//            user = new User();
//            user.setName(userName);
//            userRepository.save(user);
//        }
//        return true;
//    }
//    public Boolean createRoom(String roomName){
//
//        if(roomRepository.existsRoomByName(roomName)) {
//            room = roomRepository.findByName(roomName);
//            return false;
//        }
//        else {
//            room = new Room();
//            room.setName(roomName);
//            roomRepository.save(room);
//        }
//
//        return true;
//    }
    public Boolean createContent(String noidung){
        room = roomService.getRoom();
        user = roomService.getUser();
        content = new Content();
        content.setNoidung(noidung);
        content.setRoom(room);
        content.setUser(user);
        contentRepository.save(content);
        return true;
    }
//    public Boolean createUserRoom(){
//        userRoom = new UserRoom();
//        userRoom.setRoom_id(room.getId());
//        userRoom.setUser_id(user.getId());
//        userRoom.setRoomName(room.getName());
//        userRoom.setUserName(user.getName());
//        userRoomRepository.save(userRoom);
//        return true;
//    }
    public Boolean createUserRoomContent(){
        userRoomContent = new UserRoomContent();
        userRoomContent.setContent_id(content.getId());
        userRoomContent.setRoom_id(room.getId());
        userRoomContent.setUser_id(user.getId());
        userRoomContent.setUserName(user.getName());
        userRoomContent.setRoomName(room.getName());
        userRoomContent.setNoidung(content.getNoidung());
        userRoomContent.setCreatedDate(content.getCreatedDate());

        userRoomContentRepository.save(userRoomContent);
        return true;
    }
}
