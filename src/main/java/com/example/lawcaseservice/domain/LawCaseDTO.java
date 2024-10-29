package com.example.lawcaseservice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawCaseDTO {

    private Integer id;
    private String name;
    private Integer lawyerId;
    private Integer LawClientId;
    private Lawyer lawyer;
    private LawClient lawClient;

    public LawCaseDTO(String name, Integer lawyerId, Integer lawClientId, Lawyer lawyer, LawClient lawClient) {
        this.name = name;
        this.lawyerId = lawyerId;
        LawClientId = lawClientId;
        this.lawyer = lawyer;
        this.lawClient = lawClient;
    }
}
