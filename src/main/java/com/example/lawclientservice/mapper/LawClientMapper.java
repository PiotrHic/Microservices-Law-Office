package com.example.lawclientservice.mapper;


import com.example.lawclientservice.domain.LawClient;
import com.example.lawclientservice.domain.LawClientDTO;
import com.example.lawclientservice.domain.LawClient;
import org.springframework.stereotype.Component;

@Component
public class LawClientMapper {

    public LawClientDTO LawClientToLawClientDTO(LawClient lawClient){
        return LawClientDTO.builder()
                .id(lawClient.getId())
                .name(lawClient.getName())
                .lawCaseList(lawClient.getLawCaseList())
                .build();
    }

    public LawClient LawClientDTOToLawClient(LawClientDTO lawClientDTO){
        return LawClient.builder()
                .id(lawClientDTO.getId())
                .name(lawClientDTO.getName())
                .lawCaseList(lawClientDTO.getLawCaseList())
                .build();
    }
}
