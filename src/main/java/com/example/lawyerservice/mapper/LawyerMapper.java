package com.example.lawyerservice.mapper;


import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.domain.LawyerDTO;
import org.springframework.stereotype.Component;

@Component
public class LawyerMapper {

    public LawyerDTO lawyerToLawyerDTO(Lawyer lawyer){
        return LawyerDTO.builder()
                .id(lawyer.getId())
                .name(lawyer.getName())
                .lawCaseList(lawyer.getLawCaseList())
                .build();
    }

    public Lawyer lawyerDTOToLawyer(LawyerDTO lawyerDTO){
        return Lawyer.builder()
                .id(lawyerDTO.getId())
                .name(lawyerDTO.getName())
                .lawCaseList(lawyerDTO.getLawCaseList())
                .build();
    }
}
