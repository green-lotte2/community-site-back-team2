package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemNo;
    private  String title1;
    private  String member;
    private  String content;
    private  String status;



}
