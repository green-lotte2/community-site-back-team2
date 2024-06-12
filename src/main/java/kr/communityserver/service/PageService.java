package kr.communityserver.service;

import kr.communityserver.entity.Page;
import kr.communityserver.entity.PageUser;
import kr.communityserver.repository.PageRepository;
import kr.communityserver.repository.PageUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final PageUserRepository pageUserRepository;

    public List<PageUser> selectPages(String uid) {

        return pageUserRepository.findByUid(uid);
    }

}
