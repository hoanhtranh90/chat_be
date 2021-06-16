package com.demomongo.Repository.DAO;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;


@Data
public class ContentDAOquery {
    private Long id;
    private Long sendUserId;
    private Long roomId;

    private String noidung;
    @CreatedDate
    private Instant createdDate = Instant.now();

    private String username;
    private String roomname;


}
