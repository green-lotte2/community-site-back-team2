package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ProjectBoard")
public class ProjectBoard {

    @Id
    private String boardNo;
    private String boardTitle;
    private String createUserId;
    private int boardPosition;
    private String boardCreateDate;


}
