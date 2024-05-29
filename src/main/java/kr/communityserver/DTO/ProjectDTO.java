package kr.communityserver.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {
    private  int itemNo;
    private  String title1;
    private  String content;
    private  String status;
    private  String member;

}
