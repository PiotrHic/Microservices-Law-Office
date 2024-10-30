package com.example.lawclientservice.service;



import com.example.lawclientservice.domain.LawClient;

import java.util.List;

public interface LawClientService {
    LawClient createLawClient(LawClient lawClient);

    LawClient getLawClient (Integer lawClientid);
    List<LawClient> getAllLawClients();

    LawClient updateLawClient (Integer lawClientid, LawClient lawClient);

    String deleteLawClientByID (Integer lawClientid);
    String deleteAllLawClients();

    LawClient getLawClientForLawCase(Integer lawClientId);
}
