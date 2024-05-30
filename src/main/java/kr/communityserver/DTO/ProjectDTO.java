package kr.communityserver.DTO;

import lombok.*;

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

}
