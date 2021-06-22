package com.demomongo.Chat.Service;

import com.demomongo.Auth.DTO.UserDto;
import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.repository.UserRepository;
import com.demomongo.Chat.Modal.Content;
import com.demomongo.Chat.Modal.Room;
import com.demomongo.Chat.Repository.ContentRepository;
import com.demomongo.Chat.Repository.RoomRepository;
import com.demomongo.Chat.Repository.UserRoomContentRepository;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;


    private Room room;
    private User user;
//    @Autowired
//    private UserRoomRepository userRoomRepository;
    @Autowired
    private UserRoomContentRepository userRoomContentRepository;

    public void saveRoom(String userName, String roomName){
//        if( !userRoomRepository.existsById(room.getName()) || !)
//        {roomRepository.save(room);}
    }
    public List<Content> loadHistory(String roomName){
        Room room = roomRepository.findByName(roomName);
        List<Content> list = contentRepository.findAllByRoomId(room.getId());
        return list;
    }

    public List<User> loadUserInRoom(String roomName){
        Room room = roomRepository.findByName(roomName);
        List<User> userList = room.getUsers().stream().collect(Collectors.toList());
        return userList;
    }

    public Room getRoom(){
       return  this.room;
    }
    public User getUser(){
        return  this.user;
    }

    public void initRoom(String username,String roomName){


        if(roomRepository.existsRoomByName(roomName)){
            room = roomRepository.findByName(roomName);
        }
        else {
            room = new Room();
            room.setName(roomName);

        }

        if(userRepository.existsByUsername(username)){
            user = userRepository.findByUsername(username);
        }
        else {
            user = new User();
            user.setUsername(username);
        }

        room.setUsers(Lists.newArrayList(user));



//
        roomRepository.save(room);
    }

    public boolean checkRoomExits(String roomName) {
        return roomRepository.existsRoomByName(roomName);
    }
}
