package com.example.lawclientservice.service;


import com.example.lawclientservice.domain.LawClient;
import com.example.lawclientservice.repository.InMemoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class LawClientServiceImpl implements LawClientService {

    private final InMemoryRepository inMemoryRepository;
    @Override
    public LawClient createLawClient(LawClient LawClient) {
        return inMemoryRepository.addLawClient(LawClient);
    }

    @Override
    public LawClient getLawClient(Integer lawClientId) {
        return inMemoryRepository.getLawClientByID(lawClientId);
    }

    @Override
    public List<LawClient> getAllLawClients() {

        return inMemoryRepository.getAllLawClients();
    }

    @Override
    public LawClient updateLawClient(Integer lawClientId, LawClient lawClient) {
        return inMemoryRepository.updateLawClient(lawClientId,lawClient);
    }

    @Override
    public String deleteLawClientByID(Integer lawClientId) {
        return inMemoryRepository.deleteById(lawClientId);
    }

    @Override
    public String deleteAllLawClients() {
        return inMemoryRepository.deleteAll();
    }

    @Override
    public LawClient getLawClientForLawCase(Integer lawClientId) {
        return inMemoryRepository.getLawClientByID(lawClientId);
    }

}
