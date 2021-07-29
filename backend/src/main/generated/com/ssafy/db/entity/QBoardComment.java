package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardComment is a Querydsl query type for BoardComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardComment extends EntityPathBase<BoardComment> {

    private static final long serialVersionUID = 2138870107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardComment boardComment = new QBoardComment("boardComment");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard board;

    public final StringPath comment = createString("comment");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.time.LocalDateTime> registrationTime = createDateTime("registrationTime", java.time.LocalDateTime.class);

    public final QUser writer;

    public QBoardComment(String variable) {
        this(BoardComment.class, forVariable(variable), INITS);
    }

    public QBoardComment(Path<? extends BoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardComment(PathMetadata metadata, PathInits inits) {
        this(BoardComment.class, metadata, inits);
    }

    public QBoardComment(Class<? extends BoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.writer = inits.isInitialized("writer") ? new QUser(forProperty("writer"), inits.get("writer")) : null;
    }

}

