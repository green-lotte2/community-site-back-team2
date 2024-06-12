package kr.communityserver.controller.page;

import kr.communityserver.entity.Page;
import kr.communityserver.entity.PageUser;
import kr.communityserver.service.PageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@RestController
public class PageController {

    private final PageService pageService;

    @GetMapping("/page/list")
    public List<PageUser> list(String uid) {

        return pageService.selectPages(uid);
    }

}
