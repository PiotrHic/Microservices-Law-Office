package com.example.lawcaseservice.mapper;

import com.example.lawcaseservice.domain.LawCase;
import com.example.lawcaseservice.domain.LawCaseDTO;
import org.springframework.stereotype.Component;

@Component
public class LawCaseMapper {

    public LawCaseDTO LawCaseToLawCaseDTO(LawCase LawCase){
        return LawCaseDTO.builder()
                .id(LawCase.getId())
                .name(LawCase.getName())
                .LawClientId(LawCase.getLawClientId())
                .lawyerId(LawCase.getLawyerId())
                .lawyer(LawCase.getLawyer())
                .lawClient(LawCase.getLawClient())
                .build();
    }

    public LawCase LawCaseDTOToLawCase(LawCaseDTO lawCaseDTO){
        return LawCase.builder()
                .id(lawCaseDTO.getId())
                .name(lawCaseDTO.getName())
                .LawClientId(lawCaseDTO.getLawClientId())
                .lawyerId(lawCaseDTO.getLawyerId())
                .lawyer(lawCaseDTO.getLawyer())
                .lawClient(lawCaseDTO.getLawClient())
                .build();
    }
}
