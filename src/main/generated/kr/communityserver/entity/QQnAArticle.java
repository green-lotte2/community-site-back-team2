package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQnAArticle is a Querydsl query type for QnAArticle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnAArticle extends EntityPathBase<QnAArticle> {

    private static final long serialVersionUID = 464413140L;

    public static final QQnAArticle qnAArticle = new QQnAArticle("qnAArticle");

    public final StringPath answer = createString("answer");

    public final StringPath cate = createString("cate");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> localDateTime = createDateTime("localDateTime", java.util.Date.class);

    public final NumberPath<Integer> qnaPk = createNumber("qnaPk", Integer.class);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QQnAArticle(String variable) {
        super(QnAArticle.class, forVariable(variable));
    }

    public QQnAArticle(Path<? extends QnAArticle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQnAArticle(PathMetadata metadata) {
        super(QnAArticle.class, metadata);
    }

}

