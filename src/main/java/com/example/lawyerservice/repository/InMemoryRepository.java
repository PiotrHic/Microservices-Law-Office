package com.example.lawyerservice.repository;

import com.example.lawyerservice.domain.Lawyer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class InMemoryRepository {

    Set<Lawyer> lawyers = new HashSet<>();

    public Lawyer addLawyer(Lawyer lawyer){
        lawyers.add(lawyer);
        return lawyer;
    }

    public Lawyer updateLawyer(Integer id, Lawyer lawyer){
        Lawyer lawyerToUpdate = lawyers.stream()
                .filter(lawyerA ->
                        lawyerA.getId().equals(id))
                .findFirst()
                .orElseThrow();
        lawyerToUpdate.setName(lawyer.getName());
        lawyerToUpdate.setLawCaseList(lawyer.getLawCaseList());
        return lawyer;
    }

    public Lawyer getLawyerByID(Integer id){
        return lawyers.stream()
                .filter(lawyer ->
                        lawyer.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Lawyer> getAllLawyers(){
        return lawyers.stream().toList();
    }

    public String deleteById(Integer id){
        Lawyer lawyerToDelete = lawyers.stream()
                .filter(lawyerA ->
                        lawyerA.getId().equals(id))
                .findFirst()
                .orElseThrow();
        String result = "Lawyer with id: " + lawyerToDelete.getId() + " and with name: "
                + lawyerToDelete.getName() + "was deleted!";
        lawyers.remove(lawyerToDelete);
        return result;
    }

    public String deleteAll(){
        lawyers = new HashSet<>();
        return "Database is empty!";
    }
}
