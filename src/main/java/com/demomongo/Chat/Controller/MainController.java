package com.demomongo.Chat.Controller;

import com.demomongo.Chat.Modal.Content;
import com.demomongo.Chat.Modal.Room;
import com.demomongo.Chat.Modal.User;
import com.demomongo.Chat.Service.CreateService;
import com.demomongo.Chat.Service.RoomService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/create")
public class MainController {
    @Autowired
    private CreateService createService;
    @Autowired
    private RoomService roomService;
    private String roomName;
    private String userName;
    private String noidung;

    @PostMapping("/init")
    public ResponseEntity<?> initRoom( @RequestBody ObjectNode objectNode) {
        System.out.println(objectNode.get("userName"));
        System.out.println(objectNode.get("roomName"));
        roomService.initRoom(objectNode.get("userName").asText(),objectNode.get("roomName").asText());
        return ResponseEntity.ok(objectNode);
    }
    @PostMapping("/save_changeU")
    public ResponseEntity<?> saveAndChangeUser( @RequestBody User user) {
        this.userName = user.getName();
        //chay khi khoi tao phong
//        createService.createUser(userName);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/save_changeR")
    public ResponseEntity<?> saveAndChangeRoom( @RequestBody Room room) {
        this.roomName = room.getName();
        //chay khi khoi tao phong
//        createService.createRoom(roomName);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/save_C")
    public ResponseEntity<?> saveContent(@RequestBody Content content) {
        this.noidung = content.getNoidung();
        //chay khi chat
        createService.createContent(noidung);
        return ResponseEntity.ok("ok");
    }
//    public ResponseEntity<?> saveUserRoom() {
//        //chay khi khoi tao phong
//        createService.createUserRoom();
//        return ResponseEntity.ok("ok");
//    }
    public ResponseEntity<?> saveUserRoomContent() {
        //chay khi chat
        createService.createUserRoomContent();
        return ResponseEntity.ok("ok");
    }

//    @PostMapping("/saveR_U")
//    public ResponseEntity<?> startRoom() {
//        createService.createUserRoom();
//        return ResponseEntity.ok("ok");
//    }
    @PostMapping("/send")
    public ResponseEntity<?> sendMess() {
        createService.createUserRoomContent();
        return ResponseEntity.ok("ok");
    }
}
