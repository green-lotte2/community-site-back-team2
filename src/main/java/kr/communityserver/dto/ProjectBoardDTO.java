package kr.communityserver.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectBoardDTO {

    @Id
    private int projectNo;
    private String boardName;
    private String createUserId;

}
