package kr.communityserver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectBoardDTO {

    private String boardNo;
    private String boardTitle;
    private String createUserId;
    private String boardCreateDate;

}
