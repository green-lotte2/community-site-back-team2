package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Entity
@Table(name = "chatUser")
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatUserPk;
    private String userId;
    private  int chatRoom;

}
