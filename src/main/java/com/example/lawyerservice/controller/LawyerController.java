package com.example.lawyerservice.controller;


import com.example.lawyerservice.domain.LawCase;
import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.domain.LawyerDTO;
import com.example.lawyerservice.mapper.LawyerMapper;
import com.example.lawyerservice.service.LawyerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @Autowired
    LawyerMapper lawyerMapper;

    private final String NUMBER_PATH = "{lawyerId}";
    private final String PATH_VARIABLE_PATH = "lawyerId";

    @PostMapping
    ResponseEntity<LawyerDTO> createLawyer(@RequestBody LawyerDTO lawyerDTO){
        Lawyer added = lawyerService.addLawyer(lawyerMapper.lawyerDTOToLawyer(lawyerDTO));
        LOGGER.info("Lawyer add: {}", added.toString());
        return new ResponseEntity<>(lawyerMapper.lawyerToLawyerDTO(added),
                HttpStatus.valueOf(201));
    }

    @PutMapping(NUMBER_PATH)
    ResponseEntity <LawyerDTO> updateLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId
            , @RequestBody  LawyerDTO lawyerDTO) {
        Lawyer updated = lawyerService.updateLawyer(lawyerId,
                lawyerMapper.lawyerDTOToLawyer(lawyerDTO));
        LOGGER.info("Lawyer updated: {}", updated.toString());
        return new ResponseEntity<>(lawyerMapper.lawyerToLawyerDTO(updated), HttpStatus.OK);
    }

    @GetMapping(NUMBER_PATH)
    ResponseEntity <LawyerDTO> getLawyerById(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId) {
        Lawyer founded = lawyerService.getLawyerByID(lawyerId);
        LOGGER.info("Lawyer founded: {}", founded.toString());
        return new ResponseEntity<>(lawyerMapper.lawyerToLawyerDTO(founded), HttpStatus.OK);
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
        LOGGER.info("Lawyer deleted: {}", deleted.toString());
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        lawyerService.deleteAll();
        LOGGER.info("Database is empty");
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }

    @GetMapping("toBringLawyer/" + NUMBER_PATH)
    public Lawyer findLawyerByLawyerId(@PathVariable(PATH_VARIABLE_PATH) Integer lawyerId){
        return lawyerService.getLawyerByID(lawyerId);
    };



}
