package com.example.lawyerservice.service;

import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.repository.InMemoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LawyerServiceImpl implements LawyerService {

    private final InMemoryRepository repository;
    @Override
    public Lawyer addLawyer(Lawyer lawyer) {
        return repository.addLawyer(lawyer);
    }

    @Override
    public Lawyer updateLawyer(Integer id, Lawyer lawyer) {
        return repository.updateLawyer(id,lawyer);
    }

    @Override
    public Lawyer getLawyerByID(Integer id) {
        return repository.getLawyerByID(id);
    }

    @Override
    public List<Lawyer> getAllLawyers() {
        return repository.getAllLawyers();
    }

    @Override
    public String deleteById(Integer id) {
        return repository.deleteById(1);
    }

    @Override
    public String deleteAll() {
        return repository.deleteAll();
    }
}
