package com.example.lawyerservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LawyerRepositoryTest {

    @Autowired
    LawyerRepository lawyerRepository;

    Lawyer first = Lawyer.builder()
            .name("test1")
            .createdAt(LocalDateTime.MIN)
            .lastModifiedAt(LocalDateTime.MIN)
            .createdBy("test1")
            .lastModifiedBy("test1")
            .build();

    Lawyer second = Lawyer.builder()
            .name("test2")
            .createdAt(LocalDateTime.MAX)
            .lastModifiedAt(LocalDateTime.MAX)
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();

    @BeforeEach
    void setUp(){
        LawyerRepository.deleteAll();
    }


    @Test
    void createLawyer(){
        long count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);

        LawyerRepository.save(first);

        count = LawyerRepository.count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    void getAllLawyers(){
        long count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);

        LawyerRepository.save(first);
        LawyerRepository.save(second);

        count = LawyerRepository.findAll().size();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneLawyerById(){

        long count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);

        Lawyer saved = LawyerRepository.save(first);

        count = LawyerRepository.count();

        assertThat(count).isEqualTo(1);

        Lawyer founded = LawyerRepository.getReferenceById(first.getId());

        assertThat(founded.getName()).isEqualTo("test1");

    }

    @Test
    void deleteLawyerById(){

        long count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);

        LawyerRepository.save(first);

        count = LawyerRepository.count();

        assertThat(count).isEqualTo(1);

        LawyerRepository.deleteById(first.getId());

        count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllLawyersId() {
        long count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);

        LawyerRepository.save(first);
        LawyerRepository.save(second);

        count = LawyerRepository.count();

        assertThat(count).isEqualTo(2);

        LawyerRepository.deleteAll();

        count = LawyerRepository.count();

        assertThat(count).isEqualTo(0);

    }
}
