package com.example.lawclientservice.repository;

import com.example.lawclientservice.domain.LawClient;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class InMemoryRepository {

    Set<LawClient> lawClients = new HashSet<>();

    public LawClient addLawClient(LawClient LawClient){
        lawClients.add(LawClient);
        return LawClient;
    }

    public LawClient updateLawClient(Integer id, LawClient LawClient){
        LawClient lawClientToUpdate = lawClients.stream()
                .filter(lawClientA ->
                        lawClientA.getId().equals(id))
                .findFirst()
                .orElseThrow();
        lawClientToUpdate.setName(LawClient.getName());
        lawClientToUpdate.setLawCaseList(LawClient.getLawCaseList());
        return LawClient;
    }

    public LawClient getLawClientByID(Integer id){
        return lawClients.stream()
                .filter(LawClient ->
                        LawClient.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<LawClient> getAllLawClients(){
        return lawClients.stream().toList();
    }

    public String deleteById(Integer id){
        LawClient lawClientToDelete = lawClients.stream()
                .filter(LawClientA ->
                        LawClientA.getId().equals(id))
                .findFirst()
                .orElseThrow();
        String result = "LawClient with id: " + lawClientToDelete.getId() + " and with name: "
                + lawClientToDelete.getName() + "was deleted!";
        lawClients.remove(lawClientToDelete);
        return result;
    }

    public String deleteAll(){
        lawClients = new HashSet<>();
        return "Database is empty!";
    }
}
