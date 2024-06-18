package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPageDoc is a Querydsl query type for PageDoc
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPageDoc extends EntityPathBase<PageDoc> {

    private static final long serialVersionUID = -983069657L;

    public static final QPageDoc pageDoc = new QPageDoc("pageDoc");

    public final StringPath document = createString("document");

    public final NumberPath<Integer> pdId = createNumber("pdId", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public QPageDoc(String variable) {
        super(PageDoc.class, forVariable(variable));
    }

    public QPageDoc(Path<? extends PageDoc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageDoc(PathMetadata metadata) {
        super(PageDoc.class, metadata);
    }

}

