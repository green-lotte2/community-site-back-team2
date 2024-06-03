package kr.communityserver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectUserDTO {

    private int projectUserNo;
    private int projectNo;
    private String userId;
    private String InvitationStatus;

}
