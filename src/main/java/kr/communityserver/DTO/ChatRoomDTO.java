package kr.communityserver.DTO;


import kr.communityserver.entity.User;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

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
    private  String ChatRoom;
    private Set<WebSocketSession> sessions = new HashSet<>();
}
