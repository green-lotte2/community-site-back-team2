package kr.communityserver.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.CommentDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.Comment;
import kr.communityserver.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<Comment> comment(@RequestBody CommentDTO commentDTO, HttpServletRequest req) {
        String regip = req.getRemoteAddr();
        commentDTO.setRegip(regip);
        log.info("댓글등록 : " + commentDTO);

        return commentService.insertComment(commentDTO);

    }
}














