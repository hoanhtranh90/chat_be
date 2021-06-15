package com.demomongo.Service;


import com.demomongo.Modal.Content;
import com.demomongo.Repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;
    public void save_(Content content){
        contentRepository.save(content);
    }
    public List<Content> findByRoom(String room){
        return contentRepository.findChatMessageByRoomId(room);
    }
}
