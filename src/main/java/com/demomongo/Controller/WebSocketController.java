package com.demomongo.Controller;

import com.demomongo.Modal.Content;
import com.demomongo.Service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @MessageMapping("/send/message/{roomId}")
//    @SendTo("/topic/{roomId}")
    public void  sendMessage_ (@DestinationVariable String roomId,  Content content) {
        System.out.println(content);
        this.template.convertAndSend("/topic/"+roomId, content);

        contentService.save_(content);
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
    @RequestMapping("/history")
    @CrossOrigin("http://localhost:3000")
    public List<Content> getChatHistory(@RequestParam String roomId) {
        return contentService.findByRoom(roomId);
    }
}
