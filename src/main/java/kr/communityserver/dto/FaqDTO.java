package kr.communityserver.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaqDTO {

    private int no;
    private String cate;
    private String title;
    private  String content;


}
