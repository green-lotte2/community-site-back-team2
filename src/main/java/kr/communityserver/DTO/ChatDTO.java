package kr.communityserver.DTO;

import lombok.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Date localDateTime;
    private int file;
}
