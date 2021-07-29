package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BoardComment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "writerId", referencedColumnName = "userId", nullable = false)
    private User writer;

    String comment;

    LocalDateTime registrationTime;
    @PrePersist
    public void dateTimeNow() {
        this.registrationTime = LocalDateTime.now();
    }
}
