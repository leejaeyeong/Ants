package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Dm extends BaseEntity{
    String user;
    String message;
    String roomId;
}
