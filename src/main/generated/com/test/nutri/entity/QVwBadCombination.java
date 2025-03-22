package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVwBadCombination is a Querydsl query type for VwBadCombination
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVwBadCombination extends EntityPathBase<VwBadCombination> {

    private static final long serialVersionUID = 605172541L;

    public static final QVwBadCombination vwBadCombination = new QVwBadCombination("vwBadCombination");

    public final StringPath bad = createString("bad");

    public final StringPath functionalContent = createString("functionalContent");

    public final StringPath ingredientName = createString("ingredientName");

    public final StringPath ingredientSeq = createString("ingredientSeq");

    public final StringPath link = createString("link");

    public final StringPath name = createString("name");

    public final StringPath reason = createString("reason");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QVwBadCombination(String variable) {
        super(VwBadCombination.class, forVariable(variable));
    }

    public QVwBadCombination(Path<? extends VwBadCombination> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVwBadCombination(PathMetadata metadata) {
        super(VwBadCombination.class, metadata);
    }

}

