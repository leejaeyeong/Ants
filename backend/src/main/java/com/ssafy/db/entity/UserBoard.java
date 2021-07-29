package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class UserBoard extends BaseEntity  {

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;
}
