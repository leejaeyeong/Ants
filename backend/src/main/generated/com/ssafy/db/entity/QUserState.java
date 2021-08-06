package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserState is a Querydsl query type for UserState
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserState extends EntityPathBase<UserState> {

    private static final long serialVersionUID = 700427780L;

    public static final QUserState userState = new QUserState("userState");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath state = createString("state");

    public QUserState(String variable) {
        super(UserState.class, forVariable(variable));
    }

    public QUserState(Path<? extends UserState> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserState(PathMetadata metadata) {
        super(UserState.class, metadata);
    }

}

