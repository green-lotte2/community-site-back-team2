package kr.communityserver.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private int cno;
    private int bno;
    private String content;
    private String cwriter;
    private String nick;
    private String rdate;
    private String regip;
    //추가 필드
    private String image;

}
