package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = -1998609382L;

    public static final QChat chat = new QChat("chat");

    public final NumberPath<Integer> chatPk = createNumber("chatPk", Integer.class);

    public final NumberPath<Integer> chatRoom = createNumber("chatRoom", Integer.class);

    public final NumberPath<Integer> file = createNumber("file", Integer.class);

    public final DateTimePath<java.util.Date> localDateTime = createDateTime("localDateTime", java.util.Date.class);

    public final StringPath message = createString("message");

    public final StringPath oName = createString("oName");

    public final StringPath sName = createString("sName");

    public final StringPath userId = createString("userId");

    public QChat(String variable) {
        super(Chat.class, forVariable(variable));
    }

    public QChat(Path<? extends Chat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChat(PathMetadata metadata) {
        super(Chat.class, metadata);
    }

}

