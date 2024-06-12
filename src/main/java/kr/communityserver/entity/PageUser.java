package kr.communityserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Entity
@Table(name = "pageUser")
public class PageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pageUserPk;
    private String uid;
    private  int pagePk;

}
