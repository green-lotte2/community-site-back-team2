package kr.communityserver.service;


import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.dto.PageResponseDTO;
import kr.communityserver.dto.ReportDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.Report;
import kr.communityserver.entity.User;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.repository.ReportRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private  final  UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPg() - 1,
                pageRequestDTO.getSize(),
                Sort.by("no").descending());
        Page<Board> pageBoard = null;
        if(pageRequestDTO.getCate().equals("all")){
            pageBoard = boardRepository.findAll(pageable);
        }else{
            pageBoard = boardRepository.findByCate(pageRequestDTO.getCate(), pageable);
        }


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

        return responseDTO;
        }

        // 글보기
        public BoardDTO get(String cate, int no) {

            Optional<Board> boardOptional = boardRepository.findByNoAndCate(no, cate);

            Board board = boardOptional.orElseThrow(()-> new RuntimeException("게시글을 찾을 수 없습니다."));

            return modelMapper.map(board, BoardDTO.class);
        }

        // 글등록
        public int register(BoardDTO boardDTO) {
            Board board = modelMapper.map(boardDTO, Board.class);
            
            // uid 찾기
            String uid  = boardDTO.getWriter();
            
            // uid 이용해서 user정보에서 nick 찾기
            String nick = userRepository.findById(uid).get().getNick();
            
            // nick값 셋팅 & 저장
            board.setNick(nick);
            Board savedBoard = boardRepository.save(board);
            return savedBoard.getNo();
        }

        // 글수정
        public BoardDTO modify(String cate, int no) {

            Optional<Board> boardOptional = boardRepository.findByNoAndCate(no, cate);

            Board board = boardOptional.orElseThrow(()-> new RuntimeException("게시글을 찾을 수 없습니다."));

            return modelMapper.map(board, BoardDTO.class);
        }

        // 글수정
        public BoardDTO modify(String cate, int no, BoardDTO boardDTO) {

            Optional<Board> boardOptional = boardRepository.findByNoAndCate(no, cate);

            Board board = boardOptional.orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

            // 수정된 데이터를 기존 게시글에 반영
            board.setTitle(boardDTO.getTitle());
            board.setContent(boardDTO.getContent());
            board.setNick(boardDTO.getNick());
            board.setWriter(boardDTO.getWriter());


            board = boardRepository.save(board);

            log.info("수정 board: " + board);

            // 수정된 게시글을 DTO로 변환하여 반환
            return modelMapper.map(board, BoardDTO.class);
        }


        public void deleteBoard(String cate, int no) {
            boardRepository.deleteByCateAndNo(cate, no);
        }


        public String reportBoard(BoardDTO boardDTO, ReportDTO reportDTO) {
            try {
                // 게시글 업데이트
                Optional<Board> existingBoard = boardRepository.findById(boardDTO.getNo());

                if (existingBoard.isPresent()) {
                    Board board = modelMapper.map(boardDTO, Board.class);
                    Board updatedBoard = boardRepository.save(board);
                    Report report = modelMapper.map(reportDTO, Report.class);
                    Report updatedReport = reportRepository.save(report);

                    log.info("신고된 게시물: " + updatedBoard);
                    log.info("신고내용: " + updatedReport);
                    return "신고가 접수되었습니다.";
                } else {
                    return "해당 게시글을 찾을 수 없습니다.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "신고 처리 중 오류가 발생했습니다.";
            }
        }



    }

