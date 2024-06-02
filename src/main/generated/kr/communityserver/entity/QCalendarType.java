package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCalendarType is a Querydsl query type for CalendarType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendarType extends EntityPathBase<CalendarType> {

    private static final long serialVersionUID = -713531814L;

    public static final QCalendarType calendarType = new QCalendarType("calendarType");

    public final StringPath backgroundColor = createString("backgroundColor");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath uid = createString("uid");

    public QCalendarType(String variable) {
        super(CalendarType.class, forVariable(variable));
    }

    public QCalendarType(Path<? extends CalendarType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCalendarType(PathMetadata metadata) {
        super(CalendarType.class, metadata);
    }

}

