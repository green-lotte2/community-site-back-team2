package kr.communityserver.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatUser {
    private int chatUserPk;
    private String userId;
    private  int chatRoom;
}
