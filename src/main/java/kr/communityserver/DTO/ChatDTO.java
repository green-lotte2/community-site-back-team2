package kr.communityserver.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private int chatPk;
    private String message;
    private int messageRoom;
    private String userId;
    private LocalDateTime localDateTime;
    private int file;
}
