package kr.communityserver.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {

    private int projectNo;
    private String projectTitle;
    private String projectInfo;
    private String userId;
    private String projectStatus;

    private String invitationStatus;

    @CreationTimestamp
    private LocalDateTime projectCreateDate;

}
