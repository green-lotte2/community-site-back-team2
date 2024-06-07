package kr.communityserver.repository;

import kr.communityserver.entity.ProjectBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectBoardRepository extends JpaRepository <ProjectBoard, String> {
}
