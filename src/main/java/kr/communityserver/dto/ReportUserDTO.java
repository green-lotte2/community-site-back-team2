package kr.communityserver.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportUserDTO {

    private int rno;
    private String reporter;
    private String reason;
    private int cno;
    private String badPerson;
    @CreationTimestamp
    private LocalDateTime rdate;


}
