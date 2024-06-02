package kr.communityserver.controller.project;

import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.DTO.PageResponseDTO;
import kr.communityserver.DTO.ProjectDTO;
import kr.communityserver.DTO.ProjectItemDTO;
import kr.communityserver.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
@RestController
public class projectController {


    private final ProjectService projectService;


    //프로젝트 출력
    @GetMapping("/project")
    public PageResponseDTO<ProjectDTO> List(@RequestParam(name = "pg") int pg,
                                            @RequestParam(name = "userId") String userId){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPg(pg);
        log.info("pageRequestDTO controller： " +pageRequestDTO);

        PageResponseDTO pageResponseDTO = projectService.selectProject(userId, pageRequestDTO);

        log.info("pageResponseDTO： " +pageResponseDTO);
        return pageResponseDTO;
    }



    //프로젝트 등록
    @PostMapping("/project/projectinsert")
    public ResponseEntity addProject(@RequestBody ProjectDTO projectDTO){
        log.info("프로젝트 : " +projectDTO.toString());

        return projectService.addProject(projectDTO);
    }

    //제목 입력
    @PostMapping("/project/insert")
    public ResponseEntity addItem( @RequestBody ProjectItemDTO projectItemDTO) {
        log.info("프로젝트 아이템 : "+ projectItemDTO.toString());

        projectService.insertTitle(projectItemDTO);

        return ResponseEntity.ok().body('k');
    }

    //프로젝트 멤버 초대
    @ResponseBody
    @GetMapping ("/projectSearchUser")
    public ResponseEntity projectSearchUser(@RequestParam(name = "userEmail")String userEmail,
                                         @RequestParam(name = "projectNo")int projectNo) {

        log.info("projectSearchUser : " +userEmail);
        log.info("projectSearchUser : " +projectNo);

        return projectService.inviteUser(userEmail, projectNo);
    }

}
