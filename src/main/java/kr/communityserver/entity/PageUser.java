package kr.communityserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "pageUser")
public class PageUser {

    @Id
    private int puId;
    private int pdId;
    private String uid;


}
