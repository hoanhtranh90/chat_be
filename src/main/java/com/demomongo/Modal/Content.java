package com.demomongo.Modal;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;


@Entity
@Data
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private Long sendUserId;
    private Long roomId;

    private String noidung;
    @CreatedDate
    private Instant createdDate = Instant.now();


}
