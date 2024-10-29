package com.example.lawcaseservice.repository;


import com.example.lawcaseservice.domain.LawCase;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepository {

    Set<LawCase> lawCases = new HashSet<>();

    public LawCase addLawCase(LawCase LawCase){
        lawCases.add(LawCase);
        return LawCase;
    }

    public LawCase updateLawCase(Integer id, LawCase LawCase){
        LawCase LawCaseToUpdate = lawCases.stream()
                .filter(LawCaseA ->
                        LawCaseA.getId().equals(id))
                .findFirst()
                .orElseThrow();
        LawCaseToUpdate.setName(LawCase.getName());
        LawCaseToUpdate.setLawyer(LawCase.getLawyer());
        LawCaseToUpdate.setLawClient(LawCase.getLawClient());
        return LawCase;
    }

    public LawCase getLawCaseByID(Integer id){
        return lawCases.stream()
                .filter(LawCase ->
                        LawCase.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public Set<LawCase> getAllLawCases(){
        return lawCases;
    }

    public String deleteById(Integer id){
        LawCase LawCaseToDelete = lawCases.stream()
                .filter(LawCaseA ->
                        LawCaseA.getId().equals(id))
                .findFirst()
                .orElseThrow();
        String result = "LawCase with id: " + LawCaseToDelete.getId() + " and with name: "
                + LawCaseToDelete.getName() + "was deleted!";
        lawCases.remove(id);
        return result;
    }

    public String deleteAll(){
        lawCases= new HashSet<>();
        return "Database is empty!";
    }
}
