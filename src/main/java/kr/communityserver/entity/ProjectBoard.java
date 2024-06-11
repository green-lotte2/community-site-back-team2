package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    private String projectNo;
    private String boardTitle;
    private String createUserId;
    private int boardPosition;

    @CreationTimestamp
    private String boardCreateDate;


}
