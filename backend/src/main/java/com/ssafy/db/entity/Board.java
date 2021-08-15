package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Board extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "type", nullable = false)
    private BoardType boardType;

    @OneToOne
    @JoinColumn(name = "writerId", referencedColumnName = "userId", nullable = false)
    User writer;

    String title;
    String content;
    int view;
    String imageLocation;
    LocalTime time;
    LocalDate date;

    LocalDateTime registrationTime;
    @PrePersist
    public void dateTimeNow() {
        this.registrationTime = LocalDateTime.now();
        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }
}
