package kr.communityserver.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDocDTO {

    private int pdId;
    private String uid;
    private String title;
    private String document;

}
