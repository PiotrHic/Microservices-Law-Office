package com.example.lawcaseservice.service;



import com.example.lawcaseservice.domain.LawCase;
import com.example.lawcaseservice.repository.InMemoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class LawCaseServiceImpl implements LawCaseService {

    private final InMemoryRepository inMemoryRepository;
    @Override
    public LawCase createLawCase(LawCase LawCase) {
        return inMemoryRepository.addLawCase(LawCase);
    }

    @Override
    public LawCase getLawCase(Integer id) {
        return inMemoryRepository.getLawCaseByID(id);
    }

    @Override
    public Set<LawCase> getAllLawCases() {

        return inMemoryRepository.getAllLawCases();
    }

    @Override
    public LawCase updateLawCase(Integer id, LawCase LawCase) {
        return inMemoryRepository.updateLawCase(id,LawCase);
    }

    @Override
    public String deleteLawCaseByID(Integer id) {

        return inMemoryRepository.deleteById(id);
    }

    @Override
    public String deleteAllLawCases() {
        return inMemoryRepository.deleteAll();
    }
}
