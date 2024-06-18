package kr.communityserver.service;

import kr.communityserver.dto.PageDocDTO;
import kr.communityserver.entity.PageDoc;
import kr.communityserver.entity.PageUser;
import kr.communityserver.repository.PageDocRepository;
import kr.communityserver.repository.PageUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PageService {

    private final PageDocRepository pageDocRepository;
    private final PageUserRepository pageUserRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> selectPageList(String uid) {
        List<PageUser> pageList = pageUserRepository.findByUid(uid);
        List<Map<Integer, String>> titleList = new ArrayList<>();
        for(PageUser pageUser : pageList) {
            Optional optPageDoc = pageDocRepository.findById(pageUser.getPdId());
            if(optPageDoc.isPresent()) {
                PageDocDTO pageDoc = (PageDocDTO) optPageDoc.get();
                Map<Integer, String> map = new HashMap<>();
                map.put(pageDoc.getPdId(), pageDoc.getTitle());
                titleList.add(map);
            }
        }
        return ResponseEntity.ok().body(titleList);
    }

    public ResponseEntity<PageDoc> insertPage(PageDocDTO pageDocDTO){
        PageDoc pageDoc = modelMapper.map(pageDocDTO, PageDoc.class);
        return ResponseEntity.ok().body(pageDocRepository.save(pageDoc));
    }

}
