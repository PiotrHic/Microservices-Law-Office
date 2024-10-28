package com.example.lawyerservice.controller;


import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.service.LawyerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final String NUMBER_PATH = "{lawyerId}";
    private final String PATH_VARIABLE_PATH = "lawyerId";

    @PostMapping
    ResponseEntity<Lawyer> createLawyer(@RequestBody Lawyer lawyer){
        Lawyer added = lawyerService.addLawyer(lawyer);
        LOGGER.info("Lawyer add: {}", added.toString());
        return new ResponseEntity<>(lawyerService.addLawyer(added),
                HttpStatus.valueOf(201));
    }

    @PutMapping(NUMBER_PATH)
    ResponseEntity <Lawyer> updateLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId
            , @RequestBody Lawyer lawyer) {
        Lawyer updated = lawyerService.updateLawyer(lawyerId,
                lawyer);
        LOGGER.info("Lawyer updated: {}", updated.toString());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping(NUMBER_PATH)
    ResponseEntity <Lawyer> getLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId) {
        Lawyer founded = lawyerService.getLawyerByID(lawyerId);
        LOGGER.info("Lawyer founded: {}", founded.toString());
        return new ResponseEntity<>(founded, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <List<Lawyer>> getAllLawyers(){
        List<Lawyer> lawyers = lawyerService
                .getAllLawyers();

        LOGGER.info("All lawyers were founded!");
        return new ResponseEntity<>(lawyers, HttpStatus.OK);
    }

    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId){
        String deleted = lawyerService.deleteById(lawyerId);
        LOGGER.info("Lawyer founded: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawyerService.deleteAll();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

}
