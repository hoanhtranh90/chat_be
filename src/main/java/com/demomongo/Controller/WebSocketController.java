package com.demomongo.Controller;

import com.demomongo.Modal.Content;
import com.demomongo.Modal.ContentDao;
import com.demomongo.Modal.Room;
import com.demomongo.Service.ContentService;
import com.demomongo.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate template;


    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @Autowired
    private ContentService contentService;

    @Autowired
    private RoomService roomService;

    @MessageMapping("/send/message/{roomId}")
//    @SendTo("/topic/{roomId}")
    public void  sendMessage_ (@DestinationVariable String roomId,  ContentDao content) {
        System.out.println(content);
        this.template.convertAndSend("/topic/"+roomId, content);
        contentService.saveContent(content);
    }

    @PostMapping("/saveRoom")
    public ResponseEntity<?> saveRoom( @RequestBody Room room){
        roomService.saveRoom(room);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody  ContentDao content){
        contentService.saveContent1(content);
        return ResponseEntity.ok("success");
    }

    //    @MessageMapping("/send/message")
//    @SendTo("/topic/room1")
//    public ResponseEntity<?> sendMessage(Content content){
//        Map<String,String> data = new HashMap<>();
//        data.put("username",content.getUsername());
//        data.put("content",content.getContent());
//        data.put("timestamp", Long.toString(System.currentTimeMillis()));
//        chatHistory.save(data);
////        System.out.println(message);
////        System.out.println(content);
////        System.out.println("message");
////        this.template.convertAndSend("/message",  message);
//        return ResponseEntity.ok(content);
//    }
    @GetMapping("/history")
    public ResponseEntity<List<Content>> getChatHistory(@RequestParam String roomId, @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(contentService.findByRoom(roomId, pageable));
    }
}
