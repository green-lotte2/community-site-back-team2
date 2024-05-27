package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Setter
@Table(name = "chatRead")
public class ChatRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int chatPk;
    private  int chatRoom;
    private  int message;
    private String userId;
    private int status; //unread :0 read :1
}
