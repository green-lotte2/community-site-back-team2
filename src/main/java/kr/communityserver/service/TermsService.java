package kr.communityserver.service;

import kr.communityserver.dto.TermsDTO;
import kr.communityserver.entity.Terms;
import kr.communityserver.repository.TermsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TermsService {

    @Autowired
    private TermsRepository termsRepository;

    public ResponseEntity<TermsDTO> getAllTerms(){
        // 용어들을 각각 가져옵니다.
        Optional<Terms> allTerms = termsRepository.findById(1);
        Terms resultTerms = null;
        if(allTerms.isPresent()){
            resultTerms = allTerms.get();
        }

        // DTO 객체 생성 및 값 설정
        TermsDTO termsDTO = TermsDTO.builder()
                .terms(resultTerms.getTerms())
                .privacy(resultTerms.getPrivacy())
                .sms(resultTerms.getSms())
                .build();
        log.info("check"+termsDTO);
        return ResponseEntity.ok().body(termsDTO);
    }
}
