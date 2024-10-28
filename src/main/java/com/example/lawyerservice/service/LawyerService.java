package com.example.lawyerservice.service;

import com.example.lawyerservice.domain.Lawyer;

import java.util.HashSet;
import java.util.List;

public interface LawyerService {

    Lawyer addLawyer(Lawyer lawyer);

    Lawyer updateLawyer(Integer id, Lawyer lawyer);

    Lawyer getLawyerByID(Integer id);

    public List<Lawyer> getAllLawyers();

    public String deleteById(Integer id);

    public String deleteAll();
}
