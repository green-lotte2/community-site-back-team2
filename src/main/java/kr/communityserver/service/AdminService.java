package kr.communityserver.service;

import kr.communityserver.dto.FaqDTO;
import kr.communityserver.dto.QnAArticleDTO;
import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.dto.PageResponseDTO;
import kr.communityserver.dto.UserDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.Faq;
import kr.communityserver.entity.QnAArticle;
import kr.communityserver.entity.User;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.repository.CsRepository;
import kr.communityserver.repository.FaqRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private  final UserRepository userRepository;
    private  final BoardRepository boardRepository;
    private final FaqRepository faqRepository;
    private  final ModelMapper modelMapper;

    public ResponseEntity searchUsers(PageRequestDTO pageRequestDTO){
        //추후에 신고건수로 바꾸기

        Pageable pageable = pageRequestDTO.getPageable("uid");

        Page<User> pageBoard = userRepository.findAllBy(pageable);


        List<UserDTO> dtoList = pageBoard.getContent().stream()
                .map(entity -> {
                    UserDTO dto = modelMapper.map(entity, UserDTO.class);
                    return dto;
                })
                .toList();

        int total = (int) pageBoard.getTotalElements();
        PageResponseDTO<UserDTO> responseDTO = PageResponseDTO.<UserDTO>builder()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .build();

        return ResponseEntity.ok().body(responseDTO);
    }

    public ResponseEntity searchArticles(PageRequestDTO pageRequest){

        //공지사항은 신고버튼 없애기
        Pageable pageable = pageRequest.getPageable("no");
        Page<Board> pageBoard = boardRepository.findAll(pageable);


        List<BoardDTO> dtoList = pageBoard.getContent().stream()
                .map(entity -> {
                    BoardDTO dto = modelMapper.map(entity, BoardDTO.class);
                    return dto;
                })
                .toList();

        int total = (int) pageBoard.getTotalElements();

        PageResponseDTO<BoardDTO> responseDTO = PageResponseDTO.<BoardDTO>builder()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequest)
                .total(total)
                .build();

        return ResponseEntity.ok().body(responseDTO);
    }
    private  final CsRepository csRepository;

    public ResponseEntity searchQna(PageRequestDTO pageRequestDTO){
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
    }

    public ResponseEntity searchFaq(PageRequestDTO pageRequestDTO){
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


    public ResponseEntity stopUser(String uid){
        User user = userRepository.findById(uid).get();
        LocalDate  nowDate = LocalDate.now();
        LocalDate fifDate = nowDate.plusDays(15);
        user.setReportStart(String.valueOf(nowDate));
        user.setReportEnd(String.valueOf(fifDate));

        User save = userRepository.save(user);
        return ResponseEntity.ok().body(save);
    }

    public ResponseEntity unStopUser(String uid){
        User user = userRepository.findById(uid).get();

        user.setReportStart(null);
        user.setReportEnd(null);

        User save = userRepository.save(user);
        return ResponseEntity.ok().body(save);
    }

    public ResponseEntity stopArticle(int no){
        Board board = boardRepository.findById(no).get();
        board.setStatus(1);
        Board board1 = boardRepository.save(board);
        return ResponseEntity.ok().body(board1);
    }

    public ResponseEntity unStopArticle(int no){
        Board board = boardRepository.findById(no).get();
        board.setStatus(0);
        Board board1 = boardRepository.save(board);
        return ResponseEntity.ok().body(board1);
    }

    public ResponseEntity insertFaq(Faq faq){
        faqRepository.save(faq);
        return   ResponseEntity.ok().body(faq);
    }

    public ResponseEntity deleteFaq(int no){
        faqRepository.deleteById(no);
        return   ResponseEntity.ok().body(no);
    }

    public ResponseEntity modifyQna(QnAArticle qnAArticle){
        qnAArticle.setStatus("답변완료");
        csRepository.save(qnAArticle);
        return   ResponseEntity.ok().body("success");
    }


}
