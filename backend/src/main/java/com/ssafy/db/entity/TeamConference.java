package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class TeamConference extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    Conference conference;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    Team team;
}
