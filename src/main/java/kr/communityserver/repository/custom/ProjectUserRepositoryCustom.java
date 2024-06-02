package kr.communityserver.repository.custom;

import com.querydsl.core.Tuple;
import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.DTO.PageResponseDTO;
import kr.communityserver.DTO.ProjectDTO;
import kr.communityserver.entity.ProjectUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepositoryCustom {
    public PageReponseDTO<ProjectDTO> selectUserProject(String userId, PageRequestDTO pageRequestDTO, Pageable pageable);
    public ProjectUser findByProjectNoAndUserId(int projectNo, String userId);
}
