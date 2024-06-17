package kr.communityserver.repository.impl;


import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.entity.QBoard;
import kr.communityserver.entity.QUser;
import kr.communityserver.repository.custom.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QBoard qBoard = QBoard.board;
    private QUser qUser = QUser.user;

    @Override
    public Page<Tuple> list(PageRequestDTO pageRequestDTO, Pageable pageable) {

        String cate = pageRequestDTO.getCate();

        log.info("selectArticles...1 : " + cate);

        QueryResults<Tuple> results = jpaQueryFactory
                .select(qBoard, qUser.nick)
                                .from(qBoard).where(qBoard.cate.eq(cate).and(qBoard.parent.eq(0)))
                                .join(qUser)
                                .on(qBoard.writer.eq(qUser.uid))
                                .orderBy(qBoard.no.desc())
                                .limit(pageable.getOffset())
                                .limit(pageable.getPageSize())
                                .fetchResults();

        log.info("selectArticles...1-2 : " + results);

        List<Tuple> content = results.getResults();
        log.info("selectArticles...1-3 : " + content);

        long total = results.getTotal();

        // 페이징 처리를 위해 page 객체 리턴
        return new PageImpl<>(content, pageable, total);
    }




    @Override
    public Page<Tuple> searchArticles(PageRequestDTO pageRequestDTO, Pageable pageable) {

        String cate = pageRequestDTO.getCate();
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        // 검색 종류에 따른 where 표현식 생성
        BooleanExpression expression = null;

        if (cate.equals("all")) {

            if (type.equals("title")) {
                expression = qBoard.title.contains(keyword);
                log.info("cate all 일때...1"+cate);

            } else if (type.equals("content")) {
                expression = qBoard.content.contains(keyword);
                log.info("cate all 일때...2"+cate);

            } else if (type.equals("writer")) {
                expression = qUser.nick.contains(keyword);
                log.info("cate all 일때...3"+cate);

            }
        } else {
            if (type.equals("title")) {
                expression = qBoard.cate.eq(cate).and(qBoard.title.contains(keyword));
                log.info("cate all 아닐때...1"+cate);

            } else if (type.equals("content")) {
                expression = qBoard.cate.eq(cate).and(qBoard.content.contains(keyword));
                log.info("cate all 아닐때...2"+cate);
            } else if (type.equals("writer")) {
                expression = qBoard.cate.eq(cate).and(qBoard.parent.eq(0)).and(qUser.nick.contains(keyword));
                log.info("cate all 아닐때...3"+cate);
            }
        }


        QueryResults<Tuple> results = jpaQueryFactory
                .select(qBoard, qUser.nick)
                .from(qBoard)
                .where(expression)
                .join(qUser)
                .on(qBoard.writer.eq(qUser.uid))
                .orderBy(qBoard.no.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = results.getResults();
        long total = results.getTotal();


        return new PageImpl<>(content, pageable, total);
    }
}










