package com.example.lawyerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LawCase {

    private Integer id;
    private String name;
    private Integer lawyerId;
    private Integer LawClientId;
    private LawClient lawClient;
}
