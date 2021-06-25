package com.demomongo.Chat.Repository;

import com.demomongo.Chat.Modal.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {

    @Query(nativeQuery = true,value ="SELECT  content.*,`user`.`username` AS username, room.`name` as roomname from content \n" +
            "LEFT JOIN room ON room.id = content.room_id\n" +
            "LEFT JOIN `user` ON `user`.id = content.user_id\n" +
            "WHERE content.room_id = :room_id")
    List<Content> findAllByRoomId(Long room_id);

    @Query(nativeQuery = true,value ="SELECT  content.*,`user`.`username` AS username, room.`name` as roomname from content \n" +
            "LEFT JOIN room ON room.id = content.room_id\n" +
            "LEFT JOIN `user` ON `user`.id = content.user_id\n" +
            "WHERE content.room_id = :room_id")
    public List<Object[]> findSomethings(Long room_id);
//    List<Content> findAllByRoomI(Long room_id);

}
