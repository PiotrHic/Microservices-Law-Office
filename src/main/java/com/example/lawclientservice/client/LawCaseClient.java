package com.example.lawclientservice.client;


import com.example.lawclientservice.domain.LawCase;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface LawCaseClient {

    @GetExchange("/api/lawcase/forLawClient/{lawClientId}")
    public List<LawCase> findLawCaseByLawClientId(@PathVariable("lawClientId") Integer lawClientId);

    @GetExchange("/api/lawcase/forLawClient-withLawyer/{lawClientId}")
    public List<LawCase> findLawCaseWithLawyerByLawClientId(@PathVariable("lawClientId") Integer lawClientId);

}