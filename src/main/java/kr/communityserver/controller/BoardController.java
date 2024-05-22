package kr.communityserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Configuration
@RestController
public class BoardController {

    @GetMapping("/board/list")
    public String boardList(){
        return "/board/list";
    }
    @GetMapping("/board/view")
    public String boardView(){
        return "/board/view";
    }
    @GetMapping("/board/modify")
    public String boardModify(){
        return "/board/modify";
    }
    @GetMapping("/board/write")
    public String boardWrite(){
        return "/board/write";
    }

}
