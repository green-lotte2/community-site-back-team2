package kr.communityserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {

    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String email;
    private String hp;
    private String role;
    private String grade;
    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private String image;
    private String sms;
    private String regDate;
    private String leaveDate;

    private int report;

    private String reportStart;
    private String reportEnd;

    //추가 테이블
    @JsonIgnore
    private MultipartFile profileImg;

    private String oName;
    private String sName;

    private String profile;


}
