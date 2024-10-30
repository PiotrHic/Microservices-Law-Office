package com.example.lawcaseservice.controller;

import com.example.lawcaseservice.domain.LawCase;
import com.example.lawcaseservice.domain.LawCaseDTO;
import com.example.lawcaseservice.mapper.LawCaseMapper;
import com.example.lawcaseservice.service.LawCaseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lawcase/")
public class LawCaseController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawCaseController.class);

    private final LawCaseService lawCaseService;

    @Autowired
    LawCaseMapper lawCaseMapper;

    private final String NUMBER_PATH = "{lawCaseId}";
    private final String PATH_VARIABLE_PATH = "lawCaseId";

    @PostMapping
    ResponseEntity<LawCaseDTO> createLawCase(@RequestBody LawCaseDTO lawCaseDTO){
        LawCase added = lawCaseService.createLawCase(lawCaseMapper.lawCaseDTOToLawCase(lawCaseDTO));
        LOGGER.info("LawCase add: {}", added.toString());
        return new ResponseEntity<>(lawCaseMapper.lawCaseToLawCaseDTO(added),
                HttpStatus.valueOf(201));
    }

    @PutMapping(NUMBER_PATH)
    ResponseEntity <LawCaseDTO> updateLawCaseById(@PathVariable(PATH_VARIABLE_PATH) Integer lawCaseId
            , @RequestBody  LawCaseDTO lawCaseDTO) {
        LawCase updated = lawCaseService.updateLawCase(lawCaseId,
                lawCaseMapper.lawCaseDTOToLawCase(lawCaseDTO));
        LOGGER.info("LawCase updated: {}", updated.toString());
        return new ResponseEntity<>(lawCaseMapper.lawCaseToLawCaseDTO(updated), HttpStatus.OK);
    }

    @GetMapping(NUMBER_PATH)
    ResponseEntity <LawCaseDTO> getLawCaseById(@PathVariable(PATH_VARIABLE_PATH) Integer lawCaseId) {
        LawCase founded = lawCaseService.getLawCase(lawCaseId);
        LOGGER.info("LawCase founded: {}", founded.toString());
        return new ResponseEntity<>(lawCaseMapper.lawCaseToLawCaseDTO(founded), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <Set<LawCase>> getAllLawCases(){
        Set<LawCase> lawCases = lawCaseService
                .getAllLawCases();

        LOGGER.info("All LawCases were founded!");
        return new ResponseEntity<>(lawCases, HttpStatus.OK);
    }

    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawCaseById(@PathVariable(PATH_VARIABLE_PATH) Integer lawCaseId){
        String deleted = lawCaseService.deleteLawCaseByID(lawCaseId);
        LOGGER.info("LawCase deleted: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawCaseService.deleteAllLawCases();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

}
