package com.example.lawclientservice.domain;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawClientDTO {

    private Integer id;
    private String name;
    private List<LawCase> lawCaseList;
}
