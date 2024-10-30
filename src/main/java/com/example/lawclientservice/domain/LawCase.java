package com.example.lawclientservice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawCase {

    private Integer id;
    private String name;
    private Integer lawClientId;
    private LawClient lawClient;
}
