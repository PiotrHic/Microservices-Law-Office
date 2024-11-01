package com.example.lawclientservice.controller;


import com.example.lawclientservice.client.LawCaseClient;
import com.example.lawclientservice.domain.LawClient;
import com.example.lawclientservice.domain.LawClientDTO;
import com.example.lawclientservice.mapper.LawClientMapper;
import com.example.lawclientservice.service.LawClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "It adds a new Law Client to the database")
    @ApiResponse(responseCode = "201",
            description = "Add new Law Client to the database",
            content = {@Content(mediaType =  "application/json")})
    @PostMapping
    ResponseEntity<LawClientDTO> createLawClient(@RequestBody LawClientDTO lawClientDTO){
        LawClient added = lawClientService.createLawClient(lawClientMapper.lawClientDTOToLawClient(lawClientDTO));
        LOGGER.info("LawClient add: {}", added.toString());
        return new ResponseEntity<>(lawClientMapper.lawClientToLawClientDTO(added),
                HttpStatus.valueOf(201));
    }

    @Operation(summary = "It updates Law Client with the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Law Client to the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Law Client was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @PutMapping(NUMBER_PATH)
    ResponseEntity <LawClientDTO> updateLawClientById(@PathVariable(PATH_VARIABLE_PATH) Integer lawClientId
            , @RequestBody  LawClientDTO lawClientDTO) {
        LawClient updated = lawClientService.updateLawClient(lawClientId,
                lawClientMapper.lawClientDTOToLawClient(lawClientDTO));
        LOGGER.info("LawClient updated: {}", updated.toString());
        return new ResponseEntity<>(lawClientMapper.lawClientToLawClientDTO(updated), HttpStatus.OK);
    }

    @Operation(summary = "It brings one Law Client from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get one Law Client from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Law Client was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @GetMapping(NUMBER_PATH)
    ResponseEntity <LawClientDTO> getLawClientById(@PathVariable(PATH_VARIABLE_PATH) Integer lawClientId) {
        LawClient founded = lawClientService.getLawClient(lawClientId);
        LOGGER.info("LawClient founded: {}", founded.toString());
        return new ResponseEntity<>(lawClientMapper.lawClientToLawClientDTO(founded), HttpStatus.OK);
    }

    @Operation(summary = "Takes all Law Clients from the database")
    @ApiResponse(responseCode = "200",
            description = "Gives all Law Clients from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping
    ResponseEntity <List<LawClient>> getAllLawClients(){
        List<LawClient> LawClients = lawClientService
                .getAllLawClients();

        LOGGER.info("All LawClients were founded!");
        return new ResponseEntity<>(LawClients, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Law Clients from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Law Clients from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawClientById(@PathVariable(PATH_VARIABLE_PATH) Integer LawClientId){
        String deleted = lawClientService.deleteLawClientByID(LawClientId);
        LOGGER.info("LawClient deleted: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Law Clients from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Law Clients from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawClientService.deleteAllLawClients();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

    private static final String CB = "lawclient";

    public String testFallBack(Exception e) {
        return "To jest test fallback";
    }

    @Operation(summary = "Request a list of Law Cases with Lawyer owned by the Law Client")
    @ApiResponse(responseCode = "200",
            description = "Request a list of Law Cases with Lawyer owned by the Law Client",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("toBringLawCase-withLawyer/" + NUMBER_PATH)
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public LawClient findLawCaseWithLawyersByLawClientId(@PathVariable("lawClientId") Integer lawClientId){
        LawClient founded = lawClientService.getLawClient(lawClientId);
        founded.setLawCaseList(lawCaseClient.findLawCaseWithLawyerByLawClientId(lawClientId));
        return founded;
    };

    @Operation(summary = "Request a list of Law Cases owned by the Law Client")
    @ApiResponse(responseCode = "200",
            description = "Request a list of Law Cases owned by the Law Client",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("toBringLawCase/" + NUMBER_PATH)
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public LawClient findLawCaseByLawClientId(@PathVariable(PATH_VARIABLE_PATH) Integer lawClientId){
        LawClient founded = lawClientService.getLawClient(lawClientId);
        founded.setLawCaseList(lawCaseClient.findLawCaseByLawClientId(lawClientId));
        return founded;
    };

    @Operation(summary = "Send a Law Client to the Law Case")
    @ApiResponse(responseCode = "200",
            description = "Send a Law Client to the Law Case",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawCase/{lawClientId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public LawClient findLawClientByLawClientId(@PathVariable("lawClientId") Integer lawClientId){
        return lawClientService.getLawClient(lawClientId);
    };

}
