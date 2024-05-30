package kr.communityserver.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalendarTypeDTO {

    private int id;
    private String name;
    private String backgroundColor;
    private String uid;
}
