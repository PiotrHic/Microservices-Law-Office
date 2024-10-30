package com.example.lawclientservice.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawCaseDTO {

    private Integer id;
    private String name;
    private Integer lawClientId;
    private LawClient lawClient;
}
