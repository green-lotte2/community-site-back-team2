package kr.communityserver.service;


import kr.communityserver.dto.FaqDTO;
import kr.communityserver.dto.QnAArticleDTO;
import kr.communityserver.entity.Faq;
import kr.communityserver.entity.QnAArticle;
import kr.communityserver.entity.User;
import kr.communityserver.repository.CsRepository;
import kr.communityserver.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CsService {
    private  final CsRepository csRepository;
    private final ModelMapper modelMapper;
    private final FaqRepository faqRepository;

    public ResponseEntity insertQna(QnAArticle qnAArticle){
        qnAArticle.setStatus("답변전");
        csRepository.save(qnAArticle);

        return ResponseEntity.ok().body("success");
    }

    public ResponseEntity searchContents(kr.communityserver.dto.PageRequestDTO pageRequestDTO){
        if(pageRequestDTO.getCate().equals("qna")){
            Pageable pageable = pageRequestDTO.getPageable("qnaPk");

            Page<QnAArticle> pageBoard = csRepository.findAllByOrderByQnaPkDesc(pageable);

            List<QnAArticleDTO> dtoList = pageBoard.getContent().stream()
                    .map(entity -> {
                        QnAArticleDTO dto = modelMapper.map(entity, QnAArticleDTO.class);
                        return dto;
                    })
                    .toList();

            int total = (int) pageBoard.getTotalElements();
            kr.communityserver.dto.PageResponseDTO<QnAArticleDTO> responseDTO = kr.communityserver.dto.PageResponseDTO.<QnAArticleDTO>builder()
                    .dtoList(dtoList)
                    .pageRequestDTO(pageRequestDTO)
                    .total(total)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        }else{
           String type = pageRequestDTO.getType();
           List<FaqDTO> dtoList = faqRepository.findAllByCate(type).stream().map(
                   entity->{

                       return modelMapper.map(entity , FaqDTO.class);
                   }
           ).toList();

            kr.communityserver.dto.PageResponseDTO<FaqDTO> responseDTO = kr.communityserver.dto.PageResponseDTO.<FaqDTO>builder()
                    .dtoList(dtoList)
                    .pageRequestDTO(pageRequestDTO)
                    .total(dtoList.size())
                    .build();
            return ResponseEntity.ok().body(responseDTO);

        }
    }

    public ResponseEntity lookViewHi(int no, String cate){
        if(cate.equals("qna")){
            return ResponseEntity.ok().body(csRepository.findById(no).get());
        }else{
            return  null;
        }
    }



    public ResponseEntity modifyQna(QnAArticle qnAArticle){
        csRepository.save(qnAArticle);
        return ResponseEntity.ok().body("success");
    }


    public ResponseEntity deleteQna(int no){
        csRepository.deleteById(no);
        return ResponseEntity.ok().body("success");

    }

}
