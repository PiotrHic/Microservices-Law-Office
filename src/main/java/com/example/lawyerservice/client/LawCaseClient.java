package com.example.lawyerservice.client;

import com.example.lawyerservice.domain.LawCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface LawCaseClient {

    @GetExchange("/api/lawcase/forLawyer/{lawyerId}")
    public List<LawCase> findLawCaseByLawyerId(@PathVariable("lawyerId") Integer lawyerId);

    @GetExchange("/api/lawcase/forLawyer-withLawClient/{lawyerId}")
    public List<LawCase> findLawCaseWithLawClientsByLawyerId(@PathVariable("lawyerId") Integer lawyerId);

}