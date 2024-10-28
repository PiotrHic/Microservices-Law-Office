package com.example.lawyerservice.controller;


import com.example.lawyerservice.service.LawyerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/lawyer")
public class LawyerController {

    private final LawyerService lawyerService;

    private final String NUMBER_PATH = "{lawyerId}";
    private final String PATH_VARIABLE_PATH = "lawyerId";


}
