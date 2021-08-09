package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Todo extends BaseEntity{
    LocalTime time;
    LocalDate date;
    String title;

    @ManyToOne
    @JoinColumn(name = "department", nullable = false)
    private Department department;
}
