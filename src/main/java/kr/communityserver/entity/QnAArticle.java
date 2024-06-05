package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Setter
@Table(name = "qnaArticle")
public class QnAArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qnaPk;
    private String title;
    private String cate;
    private String content;
    private  String writer;
    private  String status;
    private String answer;
    @CreationTimestamp
    private Date localDateTime;
}
