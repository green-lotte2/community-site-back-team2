package kr.communityserver.repository;

import kr.communityserver.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {
    public ProjectUser findByProjectNoAndUserId(int projectNo, String userId);
}
