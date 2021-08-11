package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDm is a Querydsl query type for Dm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDm extends EntityPathBase<Dm> {

    private static final long serialVersionUID = 930488267L;

    public static final QDm dm = new QDm("dm");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath message = createString("message");

    public final StringPath roomId = createString("roomId");

    public final StringPath user = createString("user");

    public QDm(String variable) {
        super(Dm.class, forVariable(variable));
    }

    public QDm(Path<? extends Dm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDm(PathMetadata metadata) {
        super(Dm.class, metadata);
    }

}

