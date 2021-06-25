package com.demomongo.Chat.Controller;

import com.demomongo.Auth.entity.User;
import com.demomongo.Chat.Modal.Content;
import com.demomongo.Chat.Modal.Room;
import com.demomongo.Chat.Service.CreateService;
import com.demomongo.Chat.Service.RoomService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> initRoom( @RequestBody ObjectNode objectNode) {
        System.out.println(objectNode.get("userName"));
        System.out.println(objectNode.get("roomName"));
        roomService.initRoom(objectNode.get("userName").asText(),objectNode.get("roomName").asText());
        return ResponseEntity.ok(objectNode);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> addUser( @RequestBody ObjectNode objectNode) {
        System.out.println(objectNode.get("userName"));
        System.out.println(objectNode.get("roomName"));
        roomService.addUserToRoom(objectNode.get("userName").asText(),objectNode.get("roomName").asText());
        return ResponseEntity.ok(objectNode);
    }
    @PostMapping("/check")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> checkRoomExits( @RequestBody ObjectNode objectNode) {
        return ResponseEntity.ok(roomService.checkRoomExits(objectNode.get("roomName").asText()));
    }
}
