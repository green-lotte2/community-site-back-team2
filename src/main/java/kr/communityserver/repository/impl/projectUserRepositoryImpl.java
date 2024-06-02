package kr.communityserver.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.DTO.PageResponseDTO;
import kr.communityserver.DTO.ProjectDTO;
import kr.communityserver.entity.Project;
import kr.communityserver.entity.ProjectUser;
import kr.communityserver.repository.custom.ProjectUserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class projectUserRepositoryImpl implements ProjectUserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QProjectUser qProjectUser = QProjectUser.projectUser;
    private final QProject qProject = QProject.project;
    private final ModelMapper modelMapper;

    public PageResponseDTO<ProjectDTO> selectUserProject(String userId, PageRequestDTO pageRequestDTO, Pageable pageable){

        QueryResults<Tuple> selectProject = jpaQueryFactory
                .select(qProjectUser, qProject)
                .from(qProjectUser)
                .join(qProject)
                .on(qProjectUser.projectNo.eq(qProject.projectNo))
                .where(qProjectUser.userId.eq(userId))
                .orderBy(qProject.projectCreateDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> selectProjectResult = selectProject.getResults();
        int total = (int) selectProject.getTotal();
        Page<Tuple> tuplePage = new PageImpl<>(selectProjectResult, pageable, total);

        List<ProjectDTO> projectDTOList = tuplePage.getContent().stream()
                .map(tuple -> {
                    ProjectUser projectUser = tuple.get(qProjectUser);
                    Project project = tuple.get(qProject);
                    ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);

                    // ProjectUser의 InvitationStatus를 ProjectDTO에 설정
                    projectDTO.setInvitationStatus(projectUser.getInvitationStatus());

                    return projectDTO;
                }).toList();

        return PageResponseDTO.<ProjectDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(projectDTOList)
                .total(total)
                .build();
    }

    @Override
    public ProjectUser findByProjectNoAndUserId(int projectNo, String userId) {
        return null;
    }

}
