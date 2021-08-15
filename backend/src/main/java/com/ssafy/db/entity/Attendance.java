package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
//@Table(indexes = { @Index(name = "udis",  columnList="user_id", unique = true) })

public class Attendance extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    User user;

    //    String userId;
    LocalDate date;
    LocalTime checkInTime;
    LocalTime checkOutTime;

    @PrePersist
    public void date() {
        this.date = LocalDate.now();
    }
}
