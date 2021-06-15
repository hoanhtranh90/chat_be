package com.demomongo.Repository;

import com.demomongo.Modal.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content,String> {
    List<Content> findChatMessageByRoomId(String roomId);
}
