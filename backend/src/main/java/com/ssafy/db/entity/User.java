package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne
    @JoinColumn(name = "department", nullable = false)
    private Department department;

    String name;
    String userId;
    int holiday;
    String email;
    String profileLocation;

    @ManyToOne
    @JoinColumn(name = "state", nullable = false)
    private UserState userState;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @PrePersist
    public void setUp(){
        this.holiday = 14;
        this.position = "사원";

        // 초기 유저 state는 대기중으로
        UserState userState = new UserState();
        userState.setId((long)3);
        this.userState = userState;
    }
}
