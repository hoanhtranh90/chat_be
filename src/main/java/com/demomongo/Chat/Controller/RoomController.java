package com.demomongo.Chat.Controller;

import com.demomongo.Chat.Modal.Room;
import com.demomongo.Chat.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
