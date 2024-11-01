package com.example.lawyerservice.controller;


import com.example.lawyerservice.client.LawCaseClient;
import com.example.lawyerservice.domain.LawCase;
import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.domain.LawyerDTO;
import com.example.lawyerservice.mapper.LawyerMapper;
import com.example.lawyerservice.service.LawyerService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/lawyer/")
public class LawyerController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawyerController.class);

    private final LawyerService lawyerService;

    private final LawCaseClient lawCaseClient;

    @Autowired
    LawyerMapper lawyerMapper;

    private final String NUMBER_PATH = "{lawyerId}";
    private final String PATH_VARIABLE_PATH = "lawyerId";

    @Operation(summary = "It adds a new Lawyer to the database")
    @ApiResponse(responseCode = "201",
            description = "Add new Lawyer to the database",
            content = {@Content(mediaType =  "application/json")})
    @PostMapping
    ResponseEntity<LawyerDTO> createLawyer(@RequestBody LawyerDTO lawyerDTO){
        Lawyer added = lawyerService.addLawyer(lawyerMapper.lawyerDTOToLawyer(lawyerDTO));
        LOGGER.info("Lawyer add: {}", added.toString());
        return new ResponseEntity<>(lawyerMapper.lawyerToLawyerDTO(added),
                HttpStatus.valueOf(201));
    }

    @Operation(summary = "It updates Lawyer with the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Lawyer to the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Lawyer was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @PutMapping(NUMBER_PATH)
    ResponseEntity <LawyerDTO> updateLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId
            , @RequestBody  LawyerDTO lawyerDTO) {
        Lawyer updated = lawyerService.updateLawyer(lawyerId,
                lawyerMapper.lawyerDTOToLawyer(lawyerDTO));
        LOGGER.info("Lawyer updated: {}", updated.toString());
        return new ResponseEntity<>(lawyerMapper.lawyerToLawyerDTO(updated), HttpStatus.OK);
    }

    @Operation(summary = "It brings one Lawyer from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get one Lawyer from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Lawyer was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @GetMapping(NUMBER_PATH)
    ResponseEntity <LawyerDTO> getLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId) {
        Lawyer founded = lawyerService.getLawyerByID(lawyerId);
        LOGGER.info("Lawyer founded: {}", founded.toString());
        return new ResponseEntity<>(lawyerMapper.lawyerToLawyerDTO(founded), HttpStatus.OK);
    }

    @Operation(summary = "Takes all Lawyers from the database")
    @ApiResponse(responseCode = "200",
            description = "Gives all Lawyers from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping
    ResponseEntity <List<Lawyer>> getAllLawyers(){
        List<Lawyer> lawyers = lawyerService
                .getAllLawyers();

        LOGGER.info("All lawyers were founded!");
        return new ResponseEntity<>(lawyers, HttpStatus.OK);
    }

    @Operation(summary = "It deletes one Lawyer from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Deletes one Lawyer from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Lawyer was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId){
        String deleted = lawyerService.deleteById(lawyerId);
        LOGGER.info("Lawyer deleted: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Lawyers from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Lawyers from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawyerService.deleteAll();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

    private static final String CB = "lawyer";

    public String testFallBack(Exception e) {
        return "To jest test fallback";
    }

    @Operation(summary = "Set all Law Cases owned by the lawyer  from another service")
    @ApiResponse(responseCode = "200",
            description = "Set all Law Cases owned by the lawyer from another service",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawCases/{lawyerId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public Lawyer findLawCaseByLawyerId(@PathVariable("lawyerId") Integer lawyerId){
        Lawyer founded = lawyerService.getLawyerByID(lawyerId);
        founded.setLawCaseList(lawCaseClient.findLawCaseByLawyerId(lawyerId));
        return founded;
    };

    @Operation(summary = "Set all Law Cases with the Client  owned by the lawyer from another service")
    @ApiResponse(responseCode = "200",
            description = "Set all Law Cases with the Client  owned by the lawyer from another service",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("forLawCases-withLawClient/{lawyerId}")
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public Lawyer findLawCaseWithLawClientsByLawyerId(@PathVariable("lawyerId") Integer lawyerId){
        Lawyer founded = lawyerService.getLawyerByID(lawyerId);
        founded.setLawCaseList(lawCaseClient.findLawCaseWithLawClientsByLawyerId(lawyerId));
        return founded;
    };

    @Operation(summary = "Send Lawyer to the another service")
    @ApiResponse(responseCode = "200",
            description = "Send Lawyer to the another service",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping("toBringLawyer/" + NUMBER_PATH)
    @CircuitBreaker(name = CB, fallbackMethod = "testFallBack")
    public Lawyer findLawyerByLawyerId(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId){
        return lawyerService.getLawyerByID(lawyerId);
    };



}
