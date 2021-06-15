package com.demomongo.Service;


import com.demomongo.Modal.Content;
import com.demomongo.Modal.ContentDao;
import com.demomongo.Modal.Room;
import com.demomongo.Repository.ContentRepository;
import com.demomongo.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private RoomRepository roomRepository;
    public void saveContent(ContentDao content){
        Room room = roomRepository.findByName(content.getRoomName());
        Content content1 = new Content();
        content1.setNoidung(content.getNoidung());
        content1.setUsername(content.getUsername());
        content1.setRoom(room);
        contentRepository.save(content1);
    }
    public void saveContent1(ContentDao content){
        Room room = roomRepository.existsRoomByName(content.getRoomName()) ? roomRepository.findByName(content.getRoomName()) : new Room();
        room.setName(content.getRoomName());
        Content content1 = new Content();
        content1.setNoidung(content.getNoidung());
        content1.setUsername(content.getUsername());
        content1.setRoom(room);

        room.setContents((Collection<Content>) content1);

        roomRepository.save(room);
    }

    public List<Content> findByRoom(String roomName, Pageable pageable){
        Room room = roomRepository.findByName(roomName);
        List<Content> mainList1 = contentRepository.findAllByRoomId(room.getId(),pageable);

//        List<Content> mainList1 = new ArrayList<Content>();
//        mainList1.addAll(room.getItems());

        return mainList1;
    }
}
