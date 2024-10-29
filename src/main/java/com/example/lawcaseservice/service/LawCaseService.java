package com.example.lawcaseservice.service;



import com.example.lawcaseservice.domain.LawCase;

import java.util.List;
import java.util.Set;

public interface LawCaseService {
    LawCase createLawCase(LawCase LawCase);

    LawCase getLawCase (Integer id);
    Set<LawCase> getAllLawCases();

    LawCase updateLawCase (Integer id, LawCase LawCase);

    String deleteLawCaseByID (Integer id);
    String deleteAllLawCases();

}
