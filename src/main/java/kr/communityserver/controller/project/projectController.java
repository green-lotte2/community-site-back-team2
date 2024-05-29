package kr.communityserver.controller.project;

import kr.communityserver.DTO.ProjectDTO;
import kr.communityserver.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor
@RestController
public class projectController {


    private final ProjectService projectService;


    @GetMapping("/project/select")
    public void selectItem(@RequestParam("member") String member){
        projectService.selectItem(member);
    }

    //제목 입력
    @PostMapping("/project/insert")
    public ResponseEntity addItem( @RequestBody ProjectDTO projectDTO) {
        log.info("dd"+projectDTO.toString());

        projectService.insertTitle(projectDTO);


        return ResponseEntity.ok().body('k');
    }

}
