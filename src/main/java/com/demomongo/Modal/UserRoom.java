package com.demomongo.Modal;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_room")
@Data
public class UserRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long room_id;

    private String userName;
    private String roomName;
}
