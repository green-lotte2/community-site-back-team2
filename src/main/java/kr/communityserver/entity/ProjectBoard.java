package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

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
    private int boardNo;
    private int projectNo;
    private String userId;
    private String saveItem;

}
