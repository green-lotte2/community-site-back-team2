package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectItem is a Querydsl query type for ProjectItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectItem extends EntityPathBase<ProjectItem> {

    private static final long serialVersionUID = 295106250L;

    public static final QProjectItem projectItem = new QProjectItem("projectItem");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> itemNo = createNumber("itemNo", Integer.class);

    public final StringPath member = createString("member");

    public final StringPath status = createString("status");

    public final StringPath title1 = createString("title1");

    public QProjectItem(String variable) {
        super(ProjectItem.class, forVariable(variable));
    }

    public QProjectItem(Path<? extends ProjectItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectItem(PathMetadata metadata) {
        super(ProjectItem.class, metadata);
    }

}

