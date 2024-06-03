package kr.communityserver.controller;


import jakarta.servlet.http.HttpServletRequest;
import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.dto.PageResponseDTO;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private ModelMapper modelMapper;

    /*
    @GetMapping("/board/list")
    public String boardList(){

        return "/board/list";
    } */


    @GetMapping("/board")
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){
        log.info("pageRequsestDTO : " + pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);

        return pageResponseDTO;
    }

    // 글보기
    @GetMapping("/board/{cate}/{no}")
    public ResponseEntity<BoardDTO> boardView(@PathVariable(name ="cate") String cate, @PathVariable(name ="no") int no){
        BoardDTO boardDTO = boardService.get(cate, no);
        log.info("cate뭐야 : " + cate);
        log.info("no뭐야 : " + no);

        return ResponseEntity.ok(boardDTO);
    }

    @GetMapping("/board/modify")
    public String boardModify(){
        return "/board/modify";
    }

    // 글쓰기
    @PostMapping("/board")
    public Map<String, Integer> boardWrite(HttpServletRequest req, @RequestBody BoardDTO boardDTO){

        boardDTO.setRegip(req.getRemoteAddr());
        log.info((boardDTO.toString()));

        int no = boardService.register(boardDTO);

        return Map.of("of", no);
    }
    

}
