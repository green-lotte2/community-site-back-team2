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
@Table(name = "reportUser")
public class ReportUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String reporter;
    private String reason;
    private int cno;
    private String badPerson;

    @CreationTimestamp
    private LocalDateTime rdate;
}
