package kr.communityserver.service;

import kr.communityserver.DTO.QnAArticleDTO;
import kr.communityserver.entity.QnAArticle;
import kr.communityserver.entity.User;
import kr.communityserver.repository.CsRepository;
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
            return  null;
        }
    }

    public ResponseEntity lookViewHi(int no, String cate){
        if(cate.equals("qna")){
            return ResponseEntity.ok().body(csRepository.findById(no).get());
        }else{
            return  null;
        }
    }


}
