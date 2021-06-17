package com.demomongo.Chat.Repository;

import com.demomongo.Chat.Modal.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findByName(String name);
    boolean existsRoomByName(String name);

}
