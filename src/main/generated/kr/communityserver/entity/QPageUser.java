package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPageUser is a Querydsl query type for PageUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPageUser extends EntityPathBase<PageUser> {

    private static final long serialVersionUID = -409877828L;

    public static final QPageUser pageUser = new QPageUser("pageUser");

    public final NumberPath<Integer> pdId = createNumber("pdId", Integer.class);

    public final NumberPath<Integer> puId = createNumber("puId", Integer.class);

    public final StringPath uid = createString("uid");

    public QPageUser(String variable) {
        super(PageUser.class, forVariable(variable));
    }

    public QPageUser(Path<? extends PageUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageUser(PathMetadata metadata) {
        super(PageUser.class, metadata);
    }

}

