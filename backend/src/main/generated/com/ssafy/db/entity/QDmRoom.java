package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDmRoom is a Querydsl query type for DmRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDmRoom extends EntityPathBase<DmRoom> {

    private static final long serialVersionUID = 1285699398L;

    public static final QDmRoom dmRoom = new QDmRoom("dmRoom");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath departmentName1 = createString("departmentName1");

    public final StringPath departmentName2 = createString("departmentName2");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath profileLocation1 = createString("profileLocation1");

    public final StringPath profileLocation2 = createString("profileLocation2");

    public final StringPath roomId = createString("roomId");

    public final StringPath user1 = createString("user1");

    public final StringPath user2 = createString("user2");

    public final StringPath username1 = createString("username1");

    public final StringPath username2 = createString("username2");

    public QDmRoom(String variable) {
        super(DmRoom.class, forVariable(variable));
    }

    public QDmRoom(Path<? extends DmRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDmRoom(PathMetadata metadata) {
        super(DmRoom.class, metadata);
    }

}

