package kr.communityserver.controller;

import kr.communityserver.dto.PageDocDTO;
import kr.communityserver.entity.PageDoc;
import kr.communityserver.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
@RestController
public class PageController {

    private final PageService pageService;

    @GetMapping("/page")
    public ResponseEntity<?> getPage(String uid) {
        log.info("이거"+uid);
        return pageService.selectPageList(uid);
    }

    @PostMapping("/page")
    public ResponseEntity<PageDoc> page(@RequestBody PageDocDTO pageDocDTO) {
        log.info("마 이거다"+pageDocDTO);
        return pageService.insertPage(pageDocDTO);
    }

    @GetMapping("/page/contents")
    public ResponseEntity<?> getPageContents(String id) {
        log.info("이거다 임마!" + id);
        return pageService.selectPage(id);
    }

}
