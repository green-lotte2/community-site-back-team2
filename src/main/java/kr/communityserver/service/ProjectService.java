package kr.communityserver.service;

import kr.communityserver.DTO.ProjectDTO;
import kr.communityserver.entity.Project;
import kr.communityserver.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProjectService {


    private final ProjectRepository projectRepository;

    //프로젝트 아이템 불러오기
    public void selectItem(String member){

    }

    //프로젝트 제목저장
    public void insertTitle(ProjectDTO projectDTO){

        Project project =new Project();
        project.setTitle1(projectDTO.getTitle1());
        project.setMember(projectDTO.getMember());
        project.setStatus(projectDTO.getStatus());

        Project titleProject = projectRepository.save(project);

        log.info("제목저장 : " +titleProject );

    }


}
