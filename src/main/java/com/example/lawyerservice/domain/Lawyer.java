package com.example.lawyerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lawyer {

    private Integer id;
    private String name;
    private List<LawCase> lawCaseList;
}
