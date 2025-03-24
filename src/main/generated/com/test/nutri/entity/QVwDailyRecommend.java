package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwDailyRecommend is a Querydsl query type for VwDailyRecommend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwDailyRecommend extends EntityPathBase<VwDailyRecommend> {

    private static final long serialVersionUID = -886689706L;

    public static final QVwDailyRecommend vwDailyRecommend = new QVwDailyRecommend("vwDailyRecommend");

    public final NumberPath<Integer> dailySeq = createNumber("dailySeq", Integer.class);

    public final StringPath functionalContent = createString("functionalContent");

    public final StringPath ingredientName = createString("ingredientName");

    public final NumberPath<Integer> ingredientSeq = createNumber("ingredientSeq", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QVwDailyRecommend(String variable) {
        super(VwDailyRecommend.class, forVariable(variable));
    }

    public QVwDailyRecommend(Path<? extends VwDailyRecommend> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwDailyRecommend(PathMetadata metadata) {
        super(VwDailyRecommend.class, metadata);
    }

}

