package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    LocalDateTime registrationTime;
    @PrePersist
    public void dateTimeNow() {
        this.registrationTime = LocalDateTime.now();
    }
}
