package kr.communityserver.repository;

import kr.communityserver.entity.PageDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageDocRepository extends JpaRepository<PageDoc, Integer> {
}
