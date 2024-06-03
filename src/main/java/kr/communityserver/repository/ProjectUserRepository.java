package kr.communityserver.repository;

import kr.communityserver.entity.ProjectUser;
import kr.communityserver.repository.custom.ProjectUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer>, ProjectUserRepositoryCustom {
    //public ProjectUser findByProjectNoAndUserId(int projectNo, String userId);
    public ProjectUser findProjectUserByProjectNoAndUserId(int projectNo, String userId);

}