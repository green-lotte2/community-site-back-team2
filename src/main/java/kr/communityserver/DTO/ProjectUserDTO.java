package kr.communityserver.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectUserDTO {

    private int projectNo;
    private String userId;
    private String InvitationStatus;

}
