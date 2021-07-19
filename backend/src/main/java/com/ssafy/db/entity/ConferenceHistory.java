package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class ConferenceHistory extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    Short action;
    Timestamp insertedTime;
}
