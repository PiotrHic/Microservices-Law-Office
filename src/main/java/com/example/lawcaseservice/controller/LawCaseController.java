package com.example.lawcaseservice.controller;

import com.example.lawcaseservice.client.LawClientClient;
import com.example.lawcaseservice.client.LawyerClient;
import com.example.lawcaseservice.domain.LawCase;
import com.example.lawcaseservice.domain.LawCaseDTO;
import com.example.lawcaseservice.mapper.LawCaseMapper;
import com.example.lawcaseservice.service.LawCaseService;
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
@RequestMapping("/api/lawcase/")
public class LawCaseController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawCaseController.class);

    private final LawCaseService lawCaseService;

    private LawyerClient lawyerClient;

    private LawClientClient lawClientClient;


    @Autowired
    LawCaseMapper lawCaseMapper;

    private final String NUMBER_PATH = "{lawCaseId}";
    private final String PATH_VARIABLE_PATH = "lawCaseId";


    @Operation(summary = "It adds a new Law Case to the database")
    @ApiResponse(responseCode = "201",
            description = "Add new Law Case to the database",
            content = {@Content(mediaType =  "application/json")})
    @PostMapping
    ResponseEntity<LawCaseDTO> createLawCase(@RequestBody LawCaseDTO lawCaseDTO){
        LawCase added = lawCaseService.createLawCase(lawCaseMapper.lawCaseDTOToLawCase(lawCaseDTO));
        LOGGER.info("LawCase add: {}", added.toString());
        return new ResponseEntity<>(lawCaseMapper.lawCaseToLawCaseDTO(added),
                HttpStatus.valueOf(201));
    }

    @Operation(summary = "It updates Law Case with the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Law Case to the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Law Case was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @PutMapping(NUMBER_PATH)
    ResponseEntity <LawCaseDTO> updateLawCaseById(@PathVariable(PATH_VARIABLE_PATH) Integer lawCaseId
            , @RequestBody  LawCaseDTO lawCaseDTO) {
        LawCase updated = lawCaseService.updateLawCase(lawCaseId,
                lawCaseMapper.lawCaseDTOToLawCase(lawCaseDTO));
        LOGGER.info("LawCase updated: {}", updated.toString());
        return new ResponseEntity<>(lawCaseMapper.lawCaseToLawCaseDTO(updated), HttpStatus.OK);
    }

    @Operation(summary = "It brings one Law Case from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get one Law Case from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Law Case was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @GetMapping(NUMBER_PATH)
    ResponseEntity <LawCaseDTO> getLawCaseById(@PathVariable(PATH_VARIABLE_PATH) Integer lawCaseId) {
        LawCase founded = lawCaseService.getLawCase(lawCaseId);
        LOGGER.info("LawCase founded: {}", founded.toString());
        return new ResponseEntity<>(lawCaseMapper.lawCaseToLawCaseDTO(founded), HttpStatus.OK);
    }

    @Operation(summary = "Takes all Law Cases from the database")
    @ApiResponse(responseCode = "200",
            description = "Gives all Law Cases from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping
    ResponseEntity <Set<LawCase>> getAllLawCases(){
        Set<LawCase> lawCases = lawCaseService
                .getAllLawCases();

        LOGGER.info("All LawCases were founded!");
        return new ResponseEntity<>(lawCases, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Law Cases from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Law Cases from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawCaseById(@PathVariable(PATH_VARIABLE_PATH) Integer lawCaseId){
        String deleted = lawCaseService.deleteLawCaseByID(lawCaseId);
        LOGGER.info("LawCase deleted: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Law Cases from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Law Cases from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawCaseService.deleteAllLawCases();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

    private static final String CB = "lawcase";

    public String testFallBack(Exception e) {
        return "To jest test fallback";
    }


    @Operation(summary = "Send List of the Law Cases to the lawyer")
    @ApiResponse(responseCode = "200",
            description = "Send List of the Law Cases to the Lawyer",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawyer/{lawyerId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public List<LawCase> findLawCaseByLawyerId(@PathVariable("lawyerId") Integer lawyerId) {
        Set<LawCase> lawCases
                = lawCaseService.getAllLawCases();
        List<LawCase> lawCasesToSend = lawCases
                .stream()
                .filter(lawCase -> lawCase.getLawyerId().equals(lawyerId))
                .toList();
        return lawCasesToSend;
    }
    @Operation(summary = "Send List of the Law Cases to the lawyer with Law Client")
    @ApiResponse(responseCode = "200",
            description = "Send List of the Law Cases to the Lawyer with Law Client",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawyer-withLawClient/{lawyerId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public List<LawCase> findLawCaseWithLawClientsByLawyerId(@PathVariable("lawyerId") Integer lawyerId){
        Set<LawCase> lawCases
                = lawCaseService.getAllLawCases();
        List<LawCase> lawCasesToSend = lawCases
                .stream()
                .filter(lawCase -> lawCase.getLawyerId().equals(lawyerId))
                .toList();
        lawCasesToSend
                .forEach(lawCase ->
                        lawCase.setLawClient
                                (lawClientClient.findLawClientByLawClientId
                                        (lawCase.getLawClientId())));
        return lawCasesToSend;
    }

    @Operation(summary = "Request and attach Lawyer to the Law Case")
    @ApiResponse(responseCode = "200",
            description = "Request and attach Lawyer to the Law Case",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("toBringLawyer/{lawyerId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public LawCase findLawyerByLawyerId(@PathVariable("lawyerId") Integer lawyerId) {
        LawCase founded = lawCaseService
                .getAllLawCases()
                .stream()
                .filter(lawCase -> lawCase.getLawyerId().equals(lawyerId)).findFirst().get();
        founded.setLawyer(lawyerClient.findLawyerByLawyerId(lawyerId));
        return founded;
    }

    @Operation(summary = "Send list of the Law Cases to the Law Client")
    @ApiResponse(responseCode = "200",
            description = "Send list of the Law Cases to the Law Client",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawClient/{lawClientId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public List<LawCase> findLawCaseByLawClientId(@PathVariable("lawClientId") Integer lawClientId){
        Set<LawCase> lawCases
                = lawCaseService.getAllLawCases();
        List<LawCase> lawCasesToSend = lawCases
                .stream()
                .filter(lawCase -> lawCase.getLawClientId().equals(lawClientId))
                .toList();
        return lawCasesToSend;
    }

    @Operation(summary = "Send list of the Law Cases to the Law Client with Lawyer")
    @ApiResponse(responseCode = "200",
            description = "Send list of the Law Cases to the Law Client with Lawyer",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawClient-withLawyer/{lawClientId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public List<LawCase> findLawCaseWithLawyerByLawClientId(@PathVariable("lawClientId") Integer lawClientId){
        List<LawCase> lawCases = findLawCaseByLawClientId(lawClientId);
        lawCases
                .forEach(lawCase -> lawCase.setLawyer
                        (lawyerClient.findLawyerByLawyerId
                                (lawCase.getLawyerId())));
        return lawCases;
    }

    @Operation(summary = "Request and Attach list of the Law Cases to the Law Client")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Law Cases from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("toBringLawClient/{lawClientId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public LawCase findLawClientForLawCase(@PathVariable("lawClientId") Integer lawClientId){
        Set<LawCase> lawCases
                = lawCaseService.getAllLawCases();

        LawCase founded = lawCases.stream()
                .filter(lawCase -> lawCase.getLawClientId().equals(lawClientId))
                .findFirst()
                .get();

        founded.setLawClient(lawClientClient.findLawClientByLawClientId(lawClientId));
        return founded;
    }

}
