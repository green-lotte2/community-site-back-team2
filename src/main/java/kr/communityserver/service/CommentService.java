package kr.communityserver.service;


import kr.communityserver.dto.CommentDTO;
import kr.communityserver.entity.Comment;
import kr.communityserver.repository.CommentRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private  final  UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Comment> insertComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        String uid = commentDTO.getCwriter();
        String nick = userRepository.findById(uid).get().getNick();

        comment.setNick(nick);
        Comment savedComment = commentRepository.save(comment);

        return ResponseEntity.ok().body(savedComment);
    }
}









