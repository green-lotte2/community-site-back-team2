package kr.communityserver.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {

    private int id;
    private String calendarId;
    private String uid;
    private String title;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;

}
