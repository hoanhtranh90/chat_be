package com.demomongo.Repository;

import com.demomongo.Modal.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    @Query("select c from Content c where c.room.name = :roomName")
    Page<Content> findAllByRoomName(String roomName, Pageable pageable);
    @Query("select c from Content c where c.room.id = :id")
    List<Content> findAllByRoomId(Long id, Pageable pageable);

}
