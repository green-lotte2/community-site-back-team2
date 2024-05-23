package kr.communityserver.DTO;


import kr.communityserver.entity.User;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
