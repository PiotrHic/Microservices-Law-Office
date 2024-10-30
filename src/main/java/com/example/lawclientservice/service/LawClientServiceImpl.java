package com.example.lawclientservice.service;


import com.example.lawclientservice.domain.LawClient;
import com.example.lawclientservice.domain.Lawyer;
import com.example.lawclientservice.exception.LawClientNotFoundException;
import com.example.lawclientservice.repository.InMemoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class LawClientServiceImpl implements LawClientService {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawClientServiceImpl.class);

    private final InMemoryRepository inMemoryRepository;
    @Override
    public LawClient createLawClient(LawClient LawClient) {
        return inMemoryRepository.addLawClient(LawClient);
    }

    @Override
    public LawClient updateLawClient(Integer lawClientId, LawClient lawClient) {
        try{
            LawClient founded = inMemoryRepository.getLawClientByID(lawClientId);
        } catch (Exception e){
            LOGGER.info("LawClient not found! id : {}", lawClientId.toString());
            throw new LawClientNotFoundException("LawClient with id: " + lawClientId + " was not found!");
        }

        return inMemoryRepository.updateLawClient(lawClientId,lawClient);
    }

    @Override
    public LawClient getLawClient(Integer lawClientId) {
        try{
            LawClient founded = inMemoryRepository.getLawClientByID(lawClientId);
        } catch (Exception e){
            LOGGER.info("LawClient not found! id : {}", lawClientId.toString());
            throw new LawClientNotFoundException("LawClient with id: " + lawClientId + " was not found!");
        }
        return inMemoryRepository.getLawClientByID(lawClientId);
    }

    @Override
    public List<LawClient> getAllLawClients() {

        return inMemoryRepository.getAllLawClients();
    }

    @Override
    public String deleteLawClientByID(Integer lawClientId) {
        try{
            LawClient founded = inMemoryRepository.getLawClientByID(lawClientId);
        } catch (Exception e){
            LOGGER.info("LawClient not found! id : {}", lawClientId.toString());
            throw new LawClientNotFoundException("LawClient with id: " + lawClientId + " was not found!");
        }
        return inMemoryRepository.deleteById(lawClientId);
    }

    @Override
    public String deleteAllLawClients() {return inMemoryRepository.deleteAll();}

    @Override
    public LawClient getLawClientForLawCase(Integer lawClientId) {
        return inMemoryRepository.getLawClientByID(lawClientId);
    }

}
