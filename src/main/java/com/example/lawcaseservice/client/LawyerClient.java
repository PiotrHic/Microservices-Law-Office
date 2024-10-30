package com.example.lawcaseservice.client;

import com.example.lawcaseservice.domain.Lawyer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface LawyerClient {

    @GetExchange("/api/lawyer/toBringLawyer/{lawyerId}")
    public Lawyer findLawyerByLawyerId(@PathVariable("lawyerId") Integer lawyerId);

}