package com.example.lawcaseservice.service;



import com.example.lawcaseservice.domain.LawCase;

import java.util.List;

public interface LawCaseService {
    LawCase createLawCase(LawCase LawCase);

    LawCase getLawCase (Integer id);
    List<LawCase> getAllLawCases();

    LawCase updateLawCase (Integer id, LawCase LawCase);

    String deleteLawCaseByID (Integer id);
    String deleteAllLawCases();

}
