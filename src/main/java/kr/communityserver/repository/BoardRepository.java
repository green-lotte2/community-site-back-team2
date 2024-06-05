package kr.communityserver.repository;

import kr.communityserver.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    public Page<Board> findByCate(String cate, Pageable pageable);

    // 글 번호와 카테고리로 글을 조회하는 메서드
    Optional<Board> findByNoAndCate(int no, String cate);
    Optional<Board> findById(int no);

    // 글 삭제
    void deleteByCateAndNo(String cate, int no);

}










