package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBoard is a Querydsl query type for UserBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserBoard extends EntityPathBase<UserMarkerBoard> {

    private static final long serialVersionUID = 684578905L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBoard userBoard = new QUserBoard("userBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard board;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser user;

    public QUserBoard(String variable) {
        this(UserMarkerBoard.class, forVariable(variable), INITS);
    }

    public QUserBoard(Path<? extends UserMarkerBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBoard(PathMetadata metadata, PathInits inits) {
        this(UserMarkerBoard.class, metadata, inits);
    }

    public QUserBoard(Class<? extends UserMarkerBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

