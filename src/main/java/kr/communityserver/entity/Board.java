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
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    private String cate;

    @Builder.Default
    private int comment = 0;

    private String content;

    @Builder.Default
    private int file = 0;

    @Builder.Default
    private int hit = 0;

    private String nick;

    @Builder.Default
    private int parent = 0;

    @CreationTimestamp
    private LocalDateTime rdate;

    private String regip;

    private String title;

    private String writer;

    private  int status;
    private  int report;

}
