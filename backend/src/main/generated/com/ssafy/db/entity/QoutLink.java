package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QoutLink is a Querydsl query type for outLink
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QoutLink extends EntityPathBase<outLink> {

    private static final long serialVersionUID = 969312902L;

    public static final QoutLink outLink = new QoutLink("outLink");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final StringPath link = createString("link");

    public final StringPath title = createString("title");

    public final StringPath userId = createString("userId");

    public QoutLink(String variable) {
        super(outLink.class, forVariable(variable));
    }

    public QoutLink(Path<? extends outLink> path) {
        super(path.getType(), path.getMetadata());
    }

    public QoutLink(PathMetadata metadata) {
        super(outLink.class, metadata);
    }

}

