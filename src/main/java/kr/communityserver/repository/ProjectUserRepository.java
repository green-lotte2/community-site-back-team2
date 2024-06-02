package kr.communityserver.repository;

import kr.communityserver.entity.ProjectUser;
import kr.communityserver.repository.custom.ProjectUserRepositoryCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer>, ProjectUserRepositoryCustom {

}
