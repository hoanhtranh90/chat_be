package com.demomongo.Chat.Controller;

import com.demomongo.Chat.Modal.Content;
import com.demomongo.Chat.Modal.Room;
import com.demomongo.Chat.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @PostMapping("/loadHistory")
    public ResponseEntity<?> getHistoryofRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.loadHistory(room.getName()));
    }

    @PostMapping("/userInRoom")
    public ResponseEntity<?> getUserInRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.loadUserInRoom(room.getName()));
    }
    @PostMapping("/sendMess")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> sendMess(@RequestBody Content content){
        return ResponseEntity.ok(roomService.sendContent(content));
    }
}
