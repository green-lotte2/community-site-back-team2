package kr.communityserver.repository.custom;

import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.dto.PageResponseDTO;
import kr.communityserver.dto.ProjectDTO;
import kr.communityserver.entity.ProjectUser;
import org.springframework.data.domain.Pageable;

public interface ProjectUserRepositoryCustom {
    public PageResponseDTO<ProjectDTO> selectUserProject(String userId, PageRequestDTO pageRequestDTO, Pageable pageable);


}
