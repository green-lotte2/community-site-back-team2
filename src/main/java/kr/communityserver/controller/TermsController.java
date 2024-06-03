package kr.communityserver.controller;

import kr.communityserver.dto.TermsDTO;
import kr.communityserver.service.TermsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TermsController {


    private final TermsService termsService;

    @GetMapping("/user/terms")
    public ResponseEntity<TermsDTO> getAllTerms(){
        log.info("aa");
        return termsService.getAllTerms();
    }
}
