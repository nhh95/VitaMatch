package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwGoodCombination is a Querydsl query type for VwGoodCombination
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwGoodCombination extends EntityPathBase<VwGoodCombination> {

    private static final long serialVersionUID = -1663328641L;

    public static final QVwGoodCombination vwGoodCombination = new QVwGoodCombination("vwGoodCombination");

    public final StringPath functionalContent = createString("functionalContent");

    public final StringPath good = createString("good");

    public final StringPath ingredientName = createString("ingredientName");

    public final StringPath ingredientSeq = createString("ingredientSeq");

    public final StringPath link = createString("link");

    public final StringPath name = createString("name");

    public final StringPath reason = createString("reason");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QVwGoodCombination(String variable) {
        super(VwGoodCombination.class, forVariable(variable));
    }

    public QVwGoodCombination(Path<? extends VwGoodCombination> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwGoodCombination(PathMetadata metadata) {
        super(VwGoodCombination.class, metadata);
    }

}

