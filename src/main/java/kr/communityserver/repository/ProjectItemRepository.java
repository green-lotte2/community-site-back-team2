package kr.communityserver.repository;

import kr.communityserver.entity.ProjectItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectItemRepository extends JpaRepository<ProjectItem, Integer> {
}
