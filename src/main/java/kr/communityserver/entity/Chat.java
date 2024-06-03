package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Setter
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatPk;
    private String message;
    private int chatRoom;
    private String userId;
    private Date localDateTime;
    private int file;
    private  String oName;
    private  String sName;

    @Transient
    private  int status;
}
