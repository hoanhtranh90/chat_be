package com.demomongo.Repository;

import com.demomongo.Modal.UserRoomContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoomContentRepository extends JpaRepository<UserRoomContent, Long> {
    List<UserRoomContent> findAllByRoomName(String roomName);
}
