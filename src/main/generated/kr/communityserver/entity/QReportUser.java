package kr.communityserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportUser is a Querydsl query type for ReportUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportUser extends EntityPathBase<ReportUser> {

    private static final long serialVersionUID = 1832710753L;

    public static final QReportUser reportUser = new QReportUser("reportUser");

    public final StringPath badPerson = createString("badPerson");

    public final NumberPath<Integer> cno = createNumber("cno", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final StringPath reason = createString("reason");

    public final StringPath reporter = createString("reporter");

    public final NumberPath<Integer> rno = createNumber("rno", Integer.class);

    public QReportUser(String variable) {
        super(ReportUser.class, forVariable(variable));
    }

    public QReportUser(Path<? extends ReportUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportUser(PathMetadata metadata) {
        super(ReportUser.class, metadata);
    }

}

