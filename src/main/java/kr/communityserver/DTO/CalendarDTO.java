package kr.communityserver.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalendarDTO {

    private int id;
    private int calendarId;
    private String uid;
    private String title;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;

}
