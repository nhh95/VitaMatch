package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -917154388L;

    public static final QMember member = new QMember("member1");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final StringPath addressExtra = createString("addressExtra");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath dob = createString("dob");

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final StringPath status = createString("status");

    public final StringPath telephone = createString("telephone");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public final StringPath zipcode = createString("zipcode");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

