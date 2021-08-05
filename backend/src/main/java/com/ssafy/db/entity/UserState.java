package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.io.Serializable;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class UserState extends BaseEntity {
    String state;
}
