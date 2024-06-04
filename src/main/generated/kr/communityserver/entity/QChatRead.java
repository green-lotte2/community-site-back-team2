package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatRead is a Querydsl query type for ChatRead
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRead extends EntityPathBase<ChatRead> {

    private static final long serialVersionUID = 167957712L;

    public static final QChatRead chatRead = new QChatRead("chatRead");

    public final NumberPath<Integer> chatPk = createNumber("chatPk", Integer.class);

    public final NumberPath<Integer> chatRoom = createNumber("chatRoom", Integer.class);

    public final NumberPath<Integer> message = createNumber("message", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath userId = createString("userId");

    public QChatRead(String variable) {
        super(ChatRead.class, forVariable(variable));
    }

    public QChatRead(Path<? extends ChatRead> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRead(PathMetadata metadata) {
        super(ChatRead.class, metadata);
    }

}

