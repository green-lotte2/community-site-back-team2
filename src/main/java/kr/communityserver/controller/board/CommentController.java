package kr.communityserver.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.CommentDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.Comment;
import kr.communityserver.entity.ReportUser;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.repository.CommentRepository;
import kr.communityserver.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/comment")
    public ResponseEntity<Comment> comment(@RequestBody CommentDTO commentDTO, HttpServletRequest req) {
        String regip = req.getRemoteAddr();
        commentDTO.setRegip(regip);

        log.info("여기! : " + commentDTO);

        Comment savedComment = commentService.insertComment(commentDTO);
        log.info("댓글등록 : " + savedComment);
        int no = savedComment.getBno();

        return ResponseEntity.ok(savedComment);

    }

    @GetMapping("/comment/{bno}")
    public List<Comment> commentList(@PathVariable int bno){
        log.info("댓글번호: " + bno);
        return  commentService.commentList(bno);
    }


    // 댓글수정

    @PutMapping("/comment/{cno}")
    public Comment modifyComment(@PathVariable("cno") int cno, @RequestBody CommentRequest request) {
        return commentService.updateComment(cno, request.getContent());
    }

    static class CommentRequest {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    // 댓글삭제
    @DeleteMapping("/comment/{cno}")
    public ResponseEntity<?> deleteComment(@PathVariable("cno") int cno) {
        Optional<Comment> commentOptional = commentRepository.findById(cno);
        if (commentOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 해당 댓글이 없는 경우 404 에러 반환
        }

        Comment comment = commentOptional.get();
        int bno = comment.getBno(); // 댓글의 글 번호 가져오기
        log.info("삭제할 댓글번호 : " + bno);

        Optional<Board> boardOptional = boardRepository.findById(bno);
        if (boardOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 해당 글이 없는 경우 404 에러 반환
        }

        commentRepository.deleteById(cno); // 댓글 삭제
        log.info("댓글삭제!!");

        return ResponseEntity.ok().build(); // 삭제 성공 시 200 OK 반환
    }

    @PostMapping("/comment/report")
    public ResponseEntity reportUser(@RequestBody ReportUser user){
        return commentService.reportUser(user);
    }




}
























