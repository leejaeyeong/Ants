package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGrpConference is a Querydsl query type for GrpConference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGrpConference extends EntityPathBase<GrpConference> {

    private static final long serialVersionUID = -135313569L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGrpConference grpConference = new QGrpConference("grpConference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QConference conference;

    public final QGrp grp;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QGrpConference(String variable) {
        this(GrpConference.class, forVariable(variable), INITS);
    }

    public QGrpConference(Path<? extends GrpConference> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGrpConference(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGrpConference(PathMetadata metadata, PathInits inits) {
        this(GrpConference.class, metadata, inits);
    }

    public QGrpConference(Class<? extends GrpConference> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new QConference(forProperty("conference"), inits.get("conference")) : null;
        this.grp = inits.isInitialized("grp") ? new QGrp(forProperty("grp")) : null;
    }

}

