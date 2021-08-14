package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class FileInfo extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "uploaderId", referencedColumnName = "userId", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "department", nullable = false)
    Department department;

    String fileName;
    LocalDate date;
    LocalTime time;
    String fileLocation;
    String fileExtension;
    String size;

    @PrePersist
    public void init() {
        this.date = LocalDate.now();
        this.department = user.getDepartment();
        this.time = LocalTime.now();
    }
}
