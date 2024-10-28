package com.example.lawyerservice.controller;


import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.service.LawyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lawyer/")
public class LawyerController {

    private final LawyerService lawyerService;

    private final String NUMBER_PATH = "{lawyerId}";
    private final String PATH_VARIABLE_PATH = "lawyerId";

    @PostMapping
    ResponseEntity<Lawyer> createLawyer(@RequestBody Lawyer lawyer){
        return new ResponseEntity<>(lawyerService.addLawyer(lawyer),
                HttpStatus.valueOf(201));
    }

    @PutMapping(NUMBER_PATH)
    ResponseEntity <Lawyer> updateLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId
            , @RequestBody Lawyer lawyer) {
        Lawyer updated = lawyerService.updateLawyer(lawyerId,
                lawyer);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping(NUMBER_PATH)
    ResponseEntity <Lawyer> getLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId) {
        Lawyer founded = lawyerService.getLawyerByID(lawyerId);
        return new ResponseEntity<>(founded, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <List<Lawyer>> getAllLawyers(){
        List<Lawyer> lawyers = lawyerService
                .getAllLawyers();
        return new ResponseEntity<>(lawyers, HttpStatus.OK);
    }

    @DeleteMapping(NUMBER_PATH)
    ResponseEntity <String> deleteLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId){
        String deleted = lawyerService.deleteById(lawyerId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawyerService.deleteAll();
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

}
