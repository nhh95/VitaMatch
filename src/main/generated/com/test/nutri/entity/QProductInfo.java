package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductInfo is a Querydsl query type for ProductInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductInfo extends EntityPathBase<ProductInfo> {

    private static final long serialVersionUID = 1329456747L;

    public static final QProductInfo productInfo = new QProductInfo("productInfo");

    public final StringPath companyName = createString("companyName");

    public final StringPath expirationDate = createString("expirationDate");

    public final StringPath functionalContent = createString("functionalContent");

    public final StringPath ingestionMethod = createString("ingestionMethod");

    public final StringPath medicationType = createString("medicationType");

    public final StringPath packagingMaterial = createString("packagingMaterial");

    public final StringPath packagingMethod = createString("packagingMethod");

    public final StringPath precautionsForIngestion = createString("precautionsForIngestion");

    public final StringPath preservation = createString("preservation");

    public final StringPath productImage = createString("productImage");

    public final StringPath productName = createString("productName");

    public final StringPath registrationDate = createString("registrationDate");

    public final StringPath reportNo = createString("reportNo");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath standardsAndSpecifications = createString("standardsAndSpecifications");

    public QProductInfo(String variable) {
        super(ProductInfo.class, forVariable(variable));
    }

    public QProductInfo(Path<? extends ProductInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductInfo(PathMetadata metadata) {
        super(ProductInfo.class, metadata);
    }

}

