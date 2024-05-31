package kr.communityserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "calendarType")
public class CalendarType {

    @Id
    private int id;
    private String name;
    private String backgroundColor;
    private String uid;

}