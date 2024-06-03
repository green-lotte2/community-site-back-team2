package kr.communityserver.service;


import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.dto.PageResponseDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.repository.BoardRepository;
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
            Board savedBoard = boardRepository.save(board);
            return savedBoard.getNo();
        }



    }

