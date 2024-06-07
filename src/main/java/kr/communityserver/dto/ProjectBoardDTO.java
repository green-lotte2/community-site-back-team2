package kr.communityserver.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectBoardDTO {

    private String boardNo;
    private String projectNo;
    private String boardTitle;
    private String createUserId;
    private int boardPosition;

    @CreationTimestamp
    private String boardCreateDate;

}
