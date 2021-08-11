package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class DmRoom extends BaseEntity{

    String user1;
    String user2;

    String username1;
    String username2;

    String departmentName1;
    String departmentName2;

    String profileLocation1;
    String profileLocation2;

    String roomId;
}
