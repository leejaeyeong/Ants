package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamConference is a Querydsl query type for TeamConference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeamConference extends EntityPathBase<TeamConference> {

    private static final long serialVersionUID = 1372263323L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamConference teamConference = new QTeamConference("teamConference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QConference conference;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QTeam team;

    public QTeamConference(String variable) {
        this(TeamConference.class, forVariable(variable), INITS);
    }

    public QTeamConference(Path<? extends TeamConference> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeamConference(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeamConference(PathMetadata metadata, PathInits inits) {
        this(TeamConference.class, metadata, inits);
    }

    public QTeamConference(Class<? extends TeamConference> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new QConference(forProperty("conference"), inits.get("conference")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

