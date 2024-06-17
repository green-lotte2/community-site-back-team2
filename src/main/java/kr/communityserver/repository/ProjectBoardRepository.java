package kr.communityserver.repository;

import kr.communityserver.dto.ProjectBoardDTO;
import kr.communityserver.entity.ProjectBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectBoardRepository extends JpaRepository <ProjectBoard, String> {
    Optional<ProjectBoard> findByProjectNo(int projectNo);

    public void deleteByProjectNo(int projectNo);
}
