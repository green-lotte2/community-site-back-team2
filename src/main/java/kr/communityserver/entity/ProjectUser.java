package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Entity
@Table(name = "ProjectUser")
public class ProjectUser {

    @Id
    private int projectUserNo;
    private int projectNo;
    private String userId;
    private String InvitationStatus;

}
