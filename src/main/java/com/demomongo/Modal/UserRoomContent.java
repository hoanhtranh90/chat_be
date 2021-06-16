package com.demomongo.Modal;

import lombok.Data;

import javax.persistence.*;

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
}
