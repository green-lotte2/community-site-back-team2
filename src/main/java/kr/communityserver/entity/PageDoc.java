package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "pageDoc")
public class PageDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pdId;
    private String uid;
    private String title;
    private String document;

}
