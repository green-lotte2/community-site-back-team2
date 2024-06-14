package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectBoard is a Querydsl query type for ProjectBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectBoard extends EntityPathBase<ProjectBoard> {

    private static final long serialVersionUID = 551741967L;

    public static final QProjectBoard projectBoard = new QProjectBoard("projectBoard");

    public final NumberPath<Integer> boardNo = createNumber("boardNo", Integer.class);

    public final NumberPath<Integer> projectNo = createNumber("projectNo", Integer.class);

    public final StringPath saveItem = createString("saveItem");

    public final StringPath userId = createString("userId");

    public QProjectBoard(String variable) {
        super(ProjectBoard.class, forVariable(variable));
    }

    public QProjectBoard(Path<? extends ProjectBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectBoard(PathMetadata metadata) {
        super(ProjectBoard.class, metadata);
    }

}

