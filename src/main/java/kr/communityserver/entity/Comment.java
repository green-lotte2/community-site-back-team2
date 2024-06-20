package kr.communityserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    private int bno;
    private String content;
    private String cwriter;
    private String nick;

    @CreationTimestamp
    private LocalDateTime rdate;

    private String regip;

    //추가 필드
    private String image;

}




