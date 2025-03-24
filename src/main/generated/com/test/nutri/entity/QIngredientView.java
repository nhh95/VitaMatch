package com.test.nutri.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIngredientView is a Querydsl query type for IngredientView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientView extends EntityPathBase<IngredientView> {

    private static final long serialVersionUID = 388196648L;

    public static final QIngredientView ingredientView = new QIngredientView("ingredientView");

    public final NumberPath<Integer> contentSeq = createNumber("contentSeq", Integer.class);

    public final StringPath dailyIntake = createString("dailyIntake");

    public final StringPath functionalContent = createString("functionalContent");

    public final NumberPath<Integer> ingredientCategory = createNumber("ingredientCategory", Integer.class);

    public final StringPath ingredientName = createString("ingredientName");

    public final NumberPath<Integer> ingredientSeq = createNumber("ingredientSeq", Integer.class);

    public final StringPath precautionsForIngestion = createString("precautionsForIngestion");

    public QIngredientView(String variable) {
        super(IngredientView.class, forVariable(variable));
    }

    public QIngredientView(Path<? extends IngredientView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngredientView(PathMetadata metadata) {
        super(IngredientView.class, metadata);
    }

}

