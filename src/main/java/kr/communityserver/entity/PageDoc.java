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
@Table(name = "pageDoc")
public class PageDoc {

    @Id
    private int pdId;
    private String uid;
    private String title;
    private String document;

}
