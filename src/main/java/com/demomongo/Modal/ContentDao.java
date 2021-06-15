package com.demomongo.Modal;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Data
public class ContentDao {
    private String username;
    private String noidung;
    private String roomName;

}
