package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Conference extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

//    int ownerId;

    @ManyToOne
    @JoinColumn(name = "conference_category", nullable = false)
    private ConferenceCategory conferenceCategory;

    LocalDateTime callStartTime;
    LocalDateTime callEndTime;
    String thumbnailUrl;
    String title;
    String description;
    boolean isActive;
}
