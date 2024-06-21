package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int calendarId;
    private String uid;
    private String title;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;

}
