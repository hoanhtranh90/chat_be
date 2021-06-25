package com.demomongo.Chat.Service;

import com.demomongo.Auth.entity.User;
import com.demomongo.Auth.repository.UserRepository;
import com.demomongo.Chat.Modal.Content;
import com.demomongo.Chat.Modal.Room;
import com.demomongo.Chat.Repository.ContentRepository;
import com.demomongo.Chat.Repository.RoomRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

    public ArrayList<HashMap<String, Object>> loadHistory(String roomName){
        Room room = roomRepository.findByName(roomName);
        List<Object[]> list = contentRepository.findSomethings(room.getId());
        ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] l = list.get(i);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("id", l[0]);
            hashMap.put("noidung", l[2]);
            hashMap.put("userName", l[5]);
            hashMap.put("roomname", l[6]);
            list1.add(hashMap);
        }

        return list1;
    }

    public List<User> loadUserInRoom(String roomName){
        room = roomRepository.findByName(roomName);
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

    boolean checkDuplicateUser(Collection<User> users, String username){
        ArrayList<User> list = new ArrayList(users);

        for (int i = 0; i < users.size(); i++) {
            User user = list.get(i);
            if(user.getUsername().equals(username)) return false;
        }
        return true;
    }
    public void addUserToRoom(String username,String roomName){
        if(roomRepository.existsRoomByName(roomName)){


            room = roomRepository.findByName(roomName);
            if(checkDuplicateUser(room.getUsers(),username)){
                user = userRepository.findByUsername(username);
                Collection<User> userCollection = room.getUsers();
                userCollection.add(user);
                room.setUsers(userCollection);
                roomRepository.save(room);

            }

        }
    }

    public boolean checkRoomExits(String roomName) {
        return roomRepository.existsRoomByName(roomName);
    }

    public String sendContent(Content content1) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userRepository.findByUsername(auth.getName());
        Content content = new Content();
        content.setNoidung(content1.getNoidung());
        content.setRoom(room);
        content.setUser(user);
        contentRepository.save(content);
        return null;
    }
}
