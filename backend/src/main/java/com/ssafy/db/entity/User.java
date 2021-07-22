package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class User extends BaseEntity implements Serializable {
    String position;

    @OneToOne
    @JoinColumn(name = "department", nullable = false)
    private Department department;

    String name;
    String userId;
    int holiday;

    @ManyToOne
    @JoinColumn(name="team", nullable = false)
    private Team team;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @PrePersist
    public void setUp(){
        this.holiday = 14;
        this.team = new Team();
        this.team.setId((long)1);
        this.setDepartment(new Department(1, "empty"));
    }

}
