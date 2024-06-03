package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectUser is a Querydsl query type for ProjectUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectUser extends EntityPathBase<ProjectUser> {

    private static final long serialVersionUID = 295462786L;

    public static final QProjectUser projectUser = new QProjectUser("projectUser");

    public final StringPath InvitationStatus = createString("InvitationStatus");

    public final NumberPath<Integer> projectNo = createNumber("projectNo", Integer.class);

    public final NumberPath<Integer> projectUserNo = createNumber("projectUserNo", Integer.class);

    public final StringPath userId = createString("userId");

    public QProjectUser(String variable) {
        super(ProjectUser.class, forVariable(variable));
    }

    public QProjectUser(Path<? extends ProjectUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectUser(PathMetadata metadata) {
        super(ProjectUser.class, metadata);
    }

}

