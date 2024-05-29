package kr.communityserver.service;


import kr.communityserver.DTO.BoardDTO;
import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.DTO.PageResponseDTO;
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

        public BoardDTO get(int no) {

            Board board = boardRepository.findById(no).orElseThrow();

            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);

            return boardDTO;
        }
    }

