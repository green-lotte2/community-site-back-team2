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
@Table(name = "chatRoom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int chatRoomPk;
    private  String roomName;
    private  int status; //단체 , dm을 구분할 것임

    @CreationTimestamp
    private LocalDateTime createDate;

    @Transient
    private  int newChat;

}
