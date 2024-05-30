package kr.communityserver.service;

import kr.communityserver.DTO.BoardDTO;
import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.DTO.PageResponseDTO;
import kr.communityserver.DTO.UserDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.User;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private  final UserRepository userRepository;
    private  final BoardRepository boardRepository;
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

    public ResponseEntity searchArticles(PageRequestDTO pageRequestDTO){

        //공지사항은 신고버튼 없애기
        Pageable pageable = pageRequestDTO.getPageable("no");
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
                .pageRequestDTO(pageRequestDTO)
                .total(total)
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

}
