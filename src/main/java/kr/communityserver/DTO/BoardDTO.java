package kr.communityserver.DTO;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private int no;
    private String cate;
    private int comment;
    private String content;
    private int file;
    private int hit;
    private String nick;
    private int parent;
    private String rdate;
    private String regip;
    private String title;
    private String writer;

    private  int status;
    private  int report;

}
