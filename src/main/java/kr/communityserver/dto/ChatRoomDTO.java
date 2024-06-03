package kr.communityserver.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {

    private  int chatRoomPk;
    private  String roomName;
    private  int status;

    private LocalDateTime createDate;

}
