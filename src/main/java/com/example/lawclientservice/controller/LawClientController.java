package com.example.lawclientservice.controller;


import com.example.lawclientservice.client.LawCaseClient;
import com.example.lawclientservice.domain.LawClient;
import com.example.lawclientservice.domain.LawClientDTO;
import com.example.lawclientservice.mapper.LawClientMapper;
import com.example.lawclientservice.service.LawClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lawclient/")
public class LawClientController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawClientController.class);

    private final LawClientService lawClientService;

    private final LawCaseClient lawCaseClient;

    @Autowired
    LawClientMapper lawClientMapper;

    private final String NUMBER_PATH = "{lawClientId}";
    private final String PATH_VARIABLE_PATH = "lawClientId";

    @PostMapping
    ResponseEntity<LawClientDTO> createLawClient(@RequestBody LawClientDTO lawClientDTO){
        LawClient added = lawClientService.createLawClient(lawClientMapper.lawClientDTOToLawClient(lawClientDTO));
        LOGGER.info("LawClient add: {}", added.toString());
        return new ResponseEntity<>(lawClientMapper.lawClientToLawClientDTO(added),
                HttpStatus.valueOf(201));
    }

    @PutMapping(NUMBER_PATH)
    ResponseEntity <LawClientDTO> updateLawClientById(@PathVariable(PATH_VARIABLE_PATH) Integer lawClientId
            , @RequestBody  LawClientDTO lawClientDTO) {
        LawClient updated = lawClientService.updateLawClient(lawClientId,
                lawClientMapper.lawClientDTOToLawClient(lawClientDTO));
        LOGGER.info("LawClient updated: {}", updated.toString());
        return new ResponseEntity<>(lawClientMapper.lawClientToLawClientDTO(updated), HttpStatus.OK);
    }

    @GetMapping(NUMBER_PATH)
    ResponseEntity <LawClientDTO> getLawClientById(@PathVariable(PATH_VARIABLE_PATH) Integer lawClientId) {
        LawClient founded = lawClientService.getLawClient(lawClientId);
        LOGGER.info("LawClient founded: {}", founded.toString());
        return new ResponseEntity<>(lawClientMapper.lawClientToLawClientDTO(founded), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <List<LawClient>> getAllLawClients(){
        List<LawClient> LawClients = lawClientService
                .getAllLawClients();

        LOGGER.info("All LawClients were founded!");
        return new ResponseEntity<>(LawClients, HttpStatus.OK);
    }

    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawClientById(@PathVariable(PATH_VARIABLE_PATH) Integer LawClientId){
        String deleted = lawClientService.deleteLawClientByID(LawClientId);
        LOGGER.info("LawClient deleted: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawClientService.deleteAllLawClients();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

    @GetMapping("forLawCase/{lawClientId}")
    public LawClient findLawClientByLawClientId(@PathVariable("lawClientId") Integer lawClientId){
        return lawClientService.getLawClient(lawClientId);
    };

    @GetMapping("toBringLawCase/" + NUMBER_PATH)
    public LawClient findLawCaseByLawClientId(@PathVariable(PATH_VARIABLE_PATH) Integer lawClientId){
        LawClient founded = lawClientService.getLawClient(lawClientId);
        founded.setLawCaseList(lawCaseClient.findLawCaseByLawClientId(lawClientId));
        return founded;
    };


}
