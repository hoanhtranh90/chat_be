package com.demomongo.Chat.Modal;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_room_content")
@Data
public class UserRoomContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long room_id;

    private Long content_id;

    private String userName;
    private String roomName;
    private String noidung;
    private Instant createdDate;
}
