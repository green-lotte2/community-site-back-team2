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

    private int boardNo;
    private int projectNo;
    private String boardName;
    private String createUserId;
    private int boardPosition;

    @CreationTimestamp
    private String boardCreateDate;

}
