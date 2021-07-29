package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Grp extends BaseEntity{
    String adminId;
    String name;
    String description;
    LocalDate generatedTime;

    @PrePersist
    public void date() {
        this.generatedTime = LocalDate.now();
    }
}
