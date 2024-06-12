package kr.communityserver.controller.project;

import kr.communityserver.dto.*;
import kr.communityserver.entity.ProjectBoard;
import kr.communityserver.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@RestController
public class ProjectController {


    private final ProjectService projectService;


    //프로젝트 출력
    @GetMapping("/project")
    public PageResponseDTO<ProjectDTO> List(@RequestParam(name = "pg") int pg){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPg(pg);
        log.info("pageRequestDTO controller： " +pageRequestDTO);

        PageResponseDTO pageResponseDTO = projectService.selectProject("test1",pageRequestDTO);

        log.info("pageResponseDTO： " +pageResponseDTO);
        return pageResponseDTO;
    }

    //프로젝트 보드 출력
    @GetMapping("/project/projectboard")
    public List<ProjectBoard> selectProjectBoard (@RequestParam(name = "projectNo") int projectNo){

        log.info("프로젝트 보드 출력 !");

        return projectService.selectProjectBoard(projectNo);

    }



    //프로젝트 등록
    @PostMapping("/project/projectinsert")
    public ResponseEntity addProject(@RequestBody ProjectDTO projectDTO){
        log.info("프로젝트 : " +projectDTO.toString());

        return projectService.addProject(projectDTO);
    }

    //보드 입력
    @PostMapping("/project/boardinsert")
    public void addBoard(@RequestBody ProjectBoardDTO projectBoardDTO){

        log.info("프로젝트 보드 : " +projectBoardDTO.toString());

        projectService.insertBoard(projectBoardDTO);
    }


    //보드 - 아이템 입력
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
