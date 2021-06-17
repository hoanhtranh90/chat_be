package com.demomongo.Auth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class Role extends BaseEntity {
    private String roleName;
}
