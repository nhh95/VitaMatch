package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwHealthRecommend is a Querydsl query type for VwHealthRecommend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwHealthRecommend extends EntityPathBase<VwHealthRecommend> {

    private static final long serialVersionUID = -2120926611L;

    public static final QVwHealthRecommend vwHealthRecommend = new QVwHealthRecommend("vwHealthRecommend");

    public final StringPath functionalContent = createString("functionalContent");

    public final NumberPath<Integer> healthSeq = createNumber("healthSeq", Integer.class);

    public final StringPath ingredientName = createString("ingredientName");

    public final NumberPath<Integer> ingredientSeq = createNumber("ingredientSeq", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QVwHealthRecommend(String variable) {
        super(VwHealthRecommend.class, forVariable(variable));
    }

    public QVwHealthRecommend(Path<? extends VwHealthRecommend> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwHealthRecommend(PathMetadata metadata) {
        super(VwHealthRecommend.class, metadata);
    }

}

