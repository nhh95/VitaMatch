package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHealthCategory is a Querydsl query type for HealthCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthCategory extends EntityPathBase<HealthCategory> {

    private static final long serialVersionUID = -1829789556L;

    public static final QHealthCategory healthCategory = new QHealthCategory("healthCategory");

    public final StringPath categoryName = createString("categoryName");

    public final StringPath health = createString("health");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath productcompany = createString("productcompany");

    public final StringPath productImage = createString("productImage");

    public final StringPath productName = createString("productName");

    public QHealthCategory(String variable) {
        super(HealthCategory.class, forVariable(variable));
    }

    public QHealthCategory(Path<? extends HealthCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthCategory(PathMetadata metadata) {
        super(HealthCategory.class, metadata);
    }

}

