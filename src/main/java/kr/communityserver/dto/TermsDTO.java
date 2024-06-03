package kr.communityserver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TermsDTO {
    private String terms;
    private String privacy;
    private String sms;
}
