package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -488805609L;

    public static final QProject project = new QProject("project");

    public final DateTimePath<java.time.LocalDateTime> projectCreateDate = createDateTime("projectCreateDate", java.time.LocalDateTime.class);

    public final StringPath projectInfo = createString("projectInfo");

    public final NumberPath<Integer> projectNo = createNumber("projectNo", Integer.class);

    public final StringPath projectStatus = createString("projectStatus");

    public final StringPath projectTitle = createString("projectTitle");

    public final StringPath userId = createString("userId");

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

