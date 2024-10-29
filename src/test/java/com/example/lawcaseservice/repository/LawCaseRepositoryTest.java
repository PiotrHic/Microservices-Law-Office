package com.example.lawcaseservice.repository;

import com.example.lawcaseservice.domain.LawCase;
import com.example.lawcaseservice.domain.LawClient;
import com.example.lawcaseservice.domain.Lawyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LawCaseRepositoryTest {

    InMemoryRepository lawCaseRepository = new InMemoryRepository();

    Lawyer lawyer = new Lawyer();
    LawClient lawClient = new LawClient();

    LawCase first = LawCase.builder()
            .id(1)
            .name("test1")
            .build();

    LawCase second = LawCase.builder()
            .id(2)
            .name("test2")
            .build();

    @BeforeEach
    void setUp(){
        lawCaseRepository.deleteAll();
    }


    @Test
    void createLawCase(){
        long count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(0);

        lawCaseRepository.addLawCase(first);

        count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(1);
    }
    @Test
    void updateLawCase(){
        long count = lawCaseRepository.LawCases.size();

        lawCaseRepository.addLawCase(first);

        lawCaseRepository.updateLawCase(first.getId(),new LawCase(1,"updated",null));

        assertThat(lawCaseRepository.getLawCaseByID(1).getName()).isEqualTo("updated");
    }



    @Test
    void getAllLawCases(){
        long count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(0);

        lawCaseRepository.addLawCase(first);
        lawCaseRepository.addLawCase(second);

        count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneLawCaseById(){

        lawCaseRepository.addLawCase(first);

        Integer num = first.getId();

        LawCase founded = lawCaseRepository.getLawCaseByID(num);

        assertThat(founded.getName()).isEqualTo("test1");

    }

    @Test
    void deleteLawCaseById(){

        lawCaseRepository.addLawCase(first);

        int count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(1);

        lawCaseRepository.deleteById(first.getId());

        count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllLawCasesId() {
        lawCaseRepository.addLawCase(first);
        lawCaseRepository.addLawCase(second);

        int count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(2);

        lawCaseRepository.deleteAll();

        count = lawCaseRepository.LawCases.size();

        assertThat(count).isEqualTo(0);

    }
}
