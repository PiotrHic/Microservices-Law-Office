package com.example.lawyerservice.repository;

import com.example.lawyerservice.domain.Lawyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LawyerRepositoryTest {

    InMemoryRepository lawyerRepository = new InMemoryRepository();

    Lawyer first = Lawyer.builder()
            .id(1)
            .name("test1")
            .build();

    Lawyer second = Lawyer.builder()
            .id(2)
            .name("test2")
            .build();

    @BeforeEach
    void setUp(){
        lawyerRepository.deleteAll();
    }


    @Test
    void createLawyer(){
        long count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(0);

        lawyerRepository.addLawyer(first);

        count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(1);
    }
    @Test
    void updateLawyer(){
        long count = lawyerRepository.lawyers.size();

        lawyerRepository.addLawyer(first);

        lawyerRepository.updateLawyer(first.getId(),new Lawyer(1,"updated",null));

        assertThat(lawyerRepository.getLawyerByID(1).getName()).isEqualTo("updated");
    }



    @Test
    void getAllLawyers(){
        long count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(0);

        lawyerRepository.addLawyer(first);
        lawyerRepository.addLawyer(second);

        count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneLawyerById(){

        lawyerRepository.addLawyer(first);

        Integer num = first.getId();

        Lawyer founded = lawyerRepository.getLawyerByID(num);

        assertThat(founded.getName()).isEqualTo("test1");

    }

    @Test
    void deleteLawyerById(){

        lawyerRepository.addLawyer(first);

        int count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(1);

        lawyerRepository.deleteById(first.getId());

        count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllLawyersId() {
        lawyerRepository.addLawyer(first);
        lawyerRepository.addLawyer(second);

        int count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(2);

        lawyerRepository.deleteAll();

        count = lawyerRepository.lawyers.size();

        assertThat(count).isEqualTo(0);

    }
}
