package com.example.lawyerservice.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerDTO {

    private Integer id;
    private String name;
    private List<LawCase> lawCaseList;
}
