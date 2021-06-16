package com.demomongo.Modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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



    private String noidung;
    @CreatedDate
    private Instant createdDate = Instant.now();


    @ManyToOne
    @JoinColumn(name = "room_id") // thông qua khóa ngoại room_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id") // thông qua khóa ngoại user_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
