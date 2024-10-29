package com.example.lawyerservice.service;

import com.example.lawyerservice.controller.LawyerController;
import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.exception.LawyerNotFoundException;
import com.example.lawyerservice.repository.InMemoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LawyerServiceImpl implements LawyerService {


    private static final Logger LOGGER
            = LoggerFactory.getLogger(LawyerController.class);

    private final InMemoryRepository repository;
    @Override
    public Lawyer addLawyer(Lawyer lawyer) {
        return repository.addLawyer(lawyer);
    }

    @Override
    public Lawyer updateLawyer(Integer id, Lawyer lawyer) {
        try{
            Lawyer founded = repository.getLawyerByID(id);
        } catch (Exception e){
            LOGGER.info("Lawyer not found! id : {}", id.toString());
            throw new LawyerNotFoundException("Lawyer with id: " + id + " was not found!");
        }
        return repository.updateLawyer(id,lawyer);
    }

    @Override
    public Lawyer getLawyerByID(Integer id) {
        try{
            Lawyer founded = repository.getLawyerByID(id);
        } catch (Exception e){
            LOGGER.info("Lawyer not found! id : {}", id.toString());
            throw new LawyerNotFoundException("Lawyer with id: " + id + " was not found!");
        }
        return repository.getLawyerByID(id);
    }

    @Override
    public List<Lawyer> getAllLawyers() {
        return repository.getAllLawyers();
    }

    @Override
    public String deleteById(Integer id) {
        try{
            Lawyer founded = repository.getLawyerByID(id);
        } catch (Exception e){
            LOGGER.info("Lawyer not found! id : {}", id.toString());
            throw new LawyerNotFoundException("Lawyer with id: " + id + " was not found!");
        }
        return repository.deleteById(1);
    }

    @Override
    public String deleteAll() {
        return repository.deleteAll();
    }
}
