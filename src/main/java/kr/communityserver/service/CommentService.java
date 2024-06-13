package kr.communityserver.service;


import kr.communityserver.dto.CommentDTO;
import kr.communityserver.entity.Comment;
import kr.communityserver.entity.User;
import kr.communityserver.repository.CommentRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private  final  UserRepository userRepository;
    private final ModelMapper modelMapper;

    public int insertComment(CommentDTO commentDTO) {
        log.info("댓글 여기왔니?" + commentDTO);

        // DTO -> Entity 변환
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        log.info("comment : " + comment);

        String uid = commentDTO.getCwriter();
        String nick = userRepository.findById(uid).get().getNick();
        log.info("uid : " + uid);
        log.info("nick : " + nick);

        comment.setNick(nick);


        Comment savedComment = commentRepository.save(comment);
        log.info("savedComment : " + savedComment);

        return savedComment.getCno();
    }

    public List<Comment> commentList(int bno) {
        log.info("댓글 글번호 : " + bno);
        return  commentRepository.findByBno(bno);
    }
}

































