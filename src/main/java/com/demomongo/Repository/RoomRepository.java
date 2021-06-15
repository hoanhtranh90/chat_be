package com.demomongo.Repository;

import com.demomongo.Modal.Content;
import com.demomongo.Modal.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findByName(String name);
    boolean existsRoomByName(String name);
}
