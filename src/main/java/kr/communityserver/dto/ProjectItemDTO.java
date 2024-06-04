package kr.communityserver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectItemDTO {
    private  int itemNo;
    private  String title1;
    private  String content;
    private  String status;
    private  String member;

}
