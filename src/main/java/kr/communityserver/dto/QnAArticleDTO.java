package kr.communityserver.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QnAArticleDTO {

    private int qnaPk;
    private String title;
    private String cate;
    private String content;
    private  String writer;
    private  String status;
    private Date localDateTime;
}