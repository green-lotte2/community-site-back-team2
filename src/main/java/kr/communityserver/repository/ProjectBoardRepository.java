package kr.communityserver.repository;

import kr.communityserver.dto.ProjectBoardDTO;
import kr.communityserver.entity.ProjectBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectBoardRepository extends JpaRepository <ProjectBoard, String> {
    List<ProjectBoard> findByProjectNo(int projectNo);
}
