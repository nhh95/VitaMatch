package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwOrganRecommend is a Querydsl query type for VwOrganRecommend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwOrganRecommend extends EntityPathBase<VwOrganRecommend> {

    private static final long serialVersionUID = 1644327774L;

    public static final QVwOrganRecommend vwOrganRecommend = new QVwOrganRecommend("vwOrganRecommend");

    public final StringPath functionalContent = createString("functionalContent");

    public final StringPath ingredientName = createString("ingredientName");

    public final NumberPath<Integer> ingredientSeq = createNumber("ingredientSeq", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> organSeq = createNumber("organSeq", Integer.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QVwOrganRecommend(String variable) {
        super(VwOrganRecommend.class, forVariable(variable));
    }

    public QVwOrganRecommend(Path<? extends VwOrganRecommend> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwOrganRecommend(PathMetadata metadata) {
        super(VwOrganRecommend.class, metadata);
    }

}

