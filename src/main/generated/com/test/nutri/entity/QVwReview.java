package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwReview is a Querydsl query type for VwReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwReview extends EntityPathBase<VwReview> {

    private static final long serialVersionUID = -91198453L;

    public static final QVwReview vwReview = new QVwReview("vwReview");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public QVwReview(String variable) {
        super(VwReview.class, forVariable(variable));
    }

    public QVwReview(Path<? extends VwReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwReview(PathMetadata metadata) {
        super(VwReview.class, metadata);
    }

}

