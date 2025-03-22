package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwGenderAgeRecommend is a Querydsl query type for VwGenderAgeRecommend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwGenderAgeRecommend extends EntityPathBase<VwGenderAgeRecommend> {

    private static final long serialVersionUID = 801412113L;

    public static final QVwGenderAgeRecommend vwGenderAgeRecommend = new QVwGenderAgeRecommend("vwGenderAgeRecommend");

    public final StringPath age = createString("age");

    public final StringPath functionalContent = createString("functionalContent");

    public final StringPath gender = createString("gender");

    public final NumberPath<Integer> genderAgeSeq = createNumber("genderAgeSeq", Integer.class);

    public final StringPath ingredientName = createString("ingredientName");

    public final NumberPath<Integer> ingredientSeq = createNumber("ingredientSeq", Integer.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QVwGenderAgeRecommend(String variable) {
        super(VwGenderAgeRecommend.class, forVariable(variable));
    }

    public QVwGenderAgeRecommend(Path<? extends VwGenderAgeRecommend> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwGenderAgeRecommend(PathMetadata metadata) {
        super(VwGenderAgeRecommend.class, metadata);
    }

}

