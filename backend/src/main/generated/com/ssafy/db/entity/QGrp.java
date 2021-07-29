package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGrp is a Querydsl query type for Grp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGrp extends EntityPathBase<Grp> {

    private static final long serialVersionUID = -1219631645L;

    public static final QGrp grp = new QGrp("grp");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath adminId = createString("adminId");

    public final StringPath description = createString("description");

    public final DatePath<java.time.LocalDate> generatedTime = createDate("generatedTime", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QGrp(String variable) {
        super(Grp.class, forVariable(variable));
    }

    public QGrp(Path<? extends Grp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGrp(PathMetadata metadata) {
        super(Grp.class, metadata);
    }

}

