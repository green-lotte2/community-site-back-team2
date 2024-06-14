package kr.communityserver.service;


import kr.communityserver.dto.CommentDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.Comment;
import kr.communityserver.entity.User;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.repository.CommentRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private  final  UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Comment insertComment(CommentDTO commentDTO) {
        log.info("댓글 여기왔니?" + commentDTO);

        // DTO -> Entity 변환
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        log.info("comment : " + comment);

        String uid = commentDTO.getCwriter();
        String nick = userRepository.findById(uid).get().getNick();
        log.info("uid : " + uid);
        log.info("nick : " + nick);

        comment.setRdate(LocalDateTime.now());
        comment.setNick(nick);

        Comment savedComment = commentRepository.save(comment);
        log.info("savedComment : " + savedComment);

        return savedComment;
    }

    public List<Comment> commentList(int bno) {
        log.info("댓글 글번호 : " + bno);
        return  commentRepository.findByBno(bno);
    }

/*
    public ResponseEntity<?> deleteComment(int no, int cno){

        log.info("no : " + no);

        // 삭제 전 조회
        Optional<Board> optBoard = boardRepository.findById(no);

        log.info("optBoard : " + optBoard);

        if(optBoard.isPresent()){
            log.info("here1");

            commentRepository.deleteById(cno);

            return ResponseEntity
                    .ok()
                    .body(optBoard.get());
        }else{
            log.info("here2");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("not found");
        }
    }
*/


}

































