package kr.communityserver.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDTO {

    private int rno;
    private String reporter;
    private String reason;
    private int bno;

    @CreationTimestamp
    private LocalDateTime rdate;


}
