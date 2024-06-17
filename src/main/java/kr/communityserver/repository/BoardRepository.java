package kr.communityserver.repository;

import kr.communityserver.entity.Board;
import kr.communityserver.repository.custom.BoardRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {

    public Page<Board> findByCate(String cate, Pageable pageable);

    public  Page<Board> findAllByOrderByReportDesc(Pageable pageable);

    public  Page<Board> findAllByOrderByReportAsc(Pageable pageable);

    // 글 번호와 카테고리로 글을 조회하는 메서드
    Optional<Board> findByNoAndCate(int no, String cate);
    Optional<Board> findById(int no);

    // 글 삭제
    void deleteByCateAndNo(String cate, int no);

    // 공지사항 최신글 5개 리스트
    List<Board> findTop5ByCateOrderByRdateDesc(String cate);


}










