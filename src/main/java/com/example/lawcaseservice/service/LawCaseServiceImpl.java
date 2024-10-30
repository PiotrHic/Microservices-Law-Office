package com.example.lawcaseservice.service;



import com.example.lawcaseservice.domain.LawCase;
import com.example.lawcaseservice.domain.LawClient;
import com.example.lawcaseservice.exception.LawCaseNotFoundException;
import com.example.lawcaseservice.repository.InMemoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class LawCaseServiceImpl implements LawCaseService {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawCaseServiceImpl.class);

    private final InMemoryRepository inMemoryRepository;
    @Override
    public LawCase createLawCase(LawCase LawCase) {
        return inMemoryRepository.addLawCase(LawCase);
    }

    @Override
    public LawCase updateLawCase(Integer id, LawCase LawCase) {
        try{
            LawCase founded = inMemoryRepository.getLawCaseByID(id);
        } catch (Exception e){
            LOGGER.info("LawCase not found! id : {}", id.toString());
            throw new LawCaseNotFoundException("LawCase with id: " + id + " was not found!");
        }
        return inMemoryRepository.updateLawCase(id,LawCase);
    }

    @Override
    public LawCase getLawCase(Integer id) {
        try{
            LawCase founded = inMemoryRepository.getLawCaseByID(id);
        } catch (Exception e){
            LOGGER.info("LawCase not found! id : {}", id.toString());
            throw new LawCaseNotFoundException("LawCase with id: " + id + " was not found!");
        }

        return inMemoryRepository.getLawCaseByID(id);
    }

    @Override
    public Set<LawCase> getAllLawCases() {

        return inMemoryRepository.getAllLawCases();
    }



    @Override
    public String deleteLawCaseByID(Integer id) {
        try{
            LawCase founded = inMemoryRepository.getLawCaseByID(id);
        } catch (Exception e){
            LOGGER.info("LawCase not found! id : {}", id.toString());
            throw new LawCaseNotFoundException("LawCase with id: " + id + " was not found!");
        }
        return inMemoryRepository.deleteById(id);
    }

    @Override
    public String deleteAllLawCases() {
        return inMemoryRepository.deleteAll();
    }
}
