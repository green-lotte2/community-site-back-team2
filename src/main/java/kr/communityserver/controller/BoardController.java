package kr.communityserver.controller;

import kr.communityserver.DTO.BoardDTO;
import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.DTO.PageResponseDTO;
import kr.communityserver.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;


    /*
    @GetMapping("/board/list")
    public String boardList(){

        return "/board/list";
    } */


    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){
        log.info("pageRequsestDTO : " + pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);

        return pageResponseDTO;
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
