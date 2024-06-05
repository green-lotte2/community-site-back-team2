package kr.communityserver.repository;

import kr.communityserver.entity.Chat;
import kr.communityserver.entity.QnAArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CsRepository extends JpaRepository<QnAArticle, Integer> {

    public Page<QnAArticle> findAllByOrderByQnaPkDesc( Pageable pageable);

}
