package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class outLink extends BaseEntity{
    String userId;

    String title;
    String image;
    String link;
}
