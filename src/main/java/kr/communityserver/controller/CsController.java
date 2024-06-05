package kr.communityserver.controller;

import kr.communityserver.entity.QnAArticle;
import kr.communityserver.service.CsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequiredArgsConstructor
public class CsController {
    @Autowired
    private CsService csService;;

    @ResponseBody
    @PostMapping("/qnaWrite")
    public ResponseEntity insertQna(@RequestBody QnAArticle qnAArticle){
        log.info(qnAArticle.toString());
        return csService.insertQna(qnAArticle);
    }


    @PostMapping("/qnaContent")
    @ResponseBody
    public ResponseEntity qnaMain(@RequestBody kr.communityserver.dto.PageRequestDTO pageRequestDTO){

        log.info(pageRequestDTO.toString());
        return  csService.searchContents(pageRequestDTO);
    }

    @GetMapping("/lookView")
    @ResponseBody
    public ResponseEntity qnaMain(@RequestParam(name = "cate") String cate , @RequestParam(name = "no") int no){
        return  csService.lookViewHi(no , cate);
    }

    @PostMapping("/qna/modify")
    @ResponseBody
    public  ResponseEntity qnaModify(@RequestBody QnAArticle qnAArticle){
        return csService.modifyQna(qnAArticle);
    }

    @GetMapping("/qna/delete")
    @ResponseBody
    public ResponseEntity qnaDelete(@RequestParam(name = "no") int no){
        return  csService.deleteQna(no);
    }
}
