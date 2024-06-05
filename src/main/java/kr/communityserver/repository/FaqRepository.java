package kr.communityserver.repository;

import kr.communityserver.entity.Board;
import kr.communityserver.entity.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {
        public List<Faq> findAllByCate(String cate);
}
