package kr.communityserver.service;


import kr.communityserver.dto.CommentDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.entity.Comment;
import kr.communityserver.entity.ReportUser;
import kr.communityserver.entity.User;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.repository.CommentRepository;
import kr.communityserver.repository.ReportUserRepository;
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
    private final ReportUserRepository reportUserRepository;

    // 댓글등록
    public Comment insertComment(CommentDTO commentDTO) {
        log.info("댓글 여기왔니?" + commentDTO);

        // DTO -> Entity 변환
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        log.info("comment : " + comment);

        String uid = commentDTO.getCwriter();
        String nick = userRepository.findById(uid).get().getNick();
        String image = userRepository.findById(uid).get().getImage();
        log.info("uid : " + uid);
        log.info("nick : " + nick);
        log.info("image : " + image);

        comment.setRdate(LocalDateTime.now());
        comment.setNick(nick);
        comment.setImage(image);

        Comment savedComment = commentRepository.save(comment);
        log.info("savedComment : " + savedComment);

        return savedComment;
    }

    // 댓글리스트
    public List<Comment> commentList(int bno) {
        log.info("댓글 글번호 : " + bno);
        return  commentRepository.findByBno(bno);
    }

    // 댓글수정
    public Comment updateComment(int cno, String content){

        Comment comment  = commentRepository.findById(cno).orElseThrow();
        comment.setContent(content);
        comment.setRdate(LocalDateTime.now());

        log.info("comment 수정 : " + comment);
        return commentRepository.save(comment);
    }

    //유저 쿠사리
    public ResponseEntity reportUser(ReportUser reportUser){
        reportUser.setBadPerson(commentRepository.findById(reportUser.getCno()).get().getCwriter());
        reportUserRepository.save(reportUser);
        User user = userRepository.findById(commentRepository.findById(reportUser.getCno()).get().getCwriter()).get();
        user.setReport(user.getReport() +1);
        userRepository.save(user);
        return ResponseEntity.ok().body("success");
    }




}

































