package kr.communityserver.service;

import kr.communityserver.dto.PageDocDTO;
import kr.communityserver.dto.PageUserDTO;
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
        List<Map<String, String>> titleList = new ArrayList<>();
        for(PageUser pageUser : pageList) {
            Optional optPageDoc = pageDocRepository.findById(pageUser.getPdId());
            if(optPageDoc.isPresent()) {
                PageDocDTO pageDoc = modelMapper.map(optPageDoc.get(),PageDocDTO.class);
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(pageDoc.getPdId()));
                map.put("title", pageDoc.getTitle());
                titleList.add(map);
            }
        }
        return ResponseEntity.ok().body(titleList);
    }

    public ResponseEntity<PageDoc> insertPage(PageDocDTO pageDocDTO){
        PageDoc pageDoc = modelMapper.map(pageDocDTO, PageDoc.class);
        PageDoc savedPageDoc = pageDocRepository.save(pageDoc);
        if(pageDocDTO.getPdId() == 0) {
            pageUserInsert(savedPageDoc);
        }
        return ResponseEntity.ok().body(savedPageDoc);
    }

    public ResponseEntity<PageUser> pageUserInsert(PageDoc pageDoc){
        PageUser pageUser = modelMapper.map(pageDoc, PageUser.class);
        log.info("이거도 되냐?"+pageUser);
        return ResponseEntity.ok().body(pageUserRepository.save(pageUser));
    }

    public ResponseEntity<PageDoc> selectPage(String id){
        Optional<PageDoc> pageDocOpt = pageDocRepository.findById(Integer.parseInt(id));
        if(pageDocOpt.isPresent()) {
            PageDoc pageDoc = modelMapper.map(pageDocOpt.get(),PageDoc.class);
            return ResponseEntity.ok().body(pageDoc);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
