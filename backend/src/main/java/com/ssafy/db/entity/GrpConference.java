package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class GrpConference extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    Conference conference;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    Grp grp;
}
