package com.demomongo.Repository;

import com.demomongo.Modal.Content;
import com.demomongo.Repository.DAO.ContentDAOquery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {

    @Query(nativeQuery = true,value ="SELECT  content.*,`user`.`name` AS username, room.`name` as roomname from content \n" +
            "LEFT JOIN room ON room.id = content.room_id\n" +
            "LEFT JOIN `user` ON `user`.id = content.send_user_id\n")
    List<Content> findAllByRoomId(Long room_id);

//    List<Content> findAllByRoomId(Long room_id);

}
