package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNews is a Querydsl query type for News
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNews extends EntityPathBase<News> {

    private static final long serialVersionUID = -1279135131L;

    public static final QNews news = new QNews("news");

    public final StringPath description = createString("description");

    public final StringPath link = createString("link");

    public final StringPath originalLink = createString("originalLink");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public QNews(String variable) {
        super(News.class, forVariable(variable));
    }

    public QNews(Path<? extends News> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNews(PathMetadata metadata) {
        super(News.class, metadata);
    }

}

