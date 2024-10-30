package com.example.lawclientservice.repository;

import com.example.lawclientservice.domain.LawClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LawClientRepositoryTest {

    InMemoryRepository lawClientRepository = new InMemoryRepository();

    LawClient first = LawClient.builder()
            .id(1)
            .name("test1")
            .build();

    LawClient second = LawClient.builder()
            .id(2)
            .name("test2")
            .build();

    @BeforeEach
    void setUp(){
        lawClientRepository.deleteAll();
    }


    @Test
    void createLawClient(){
        long count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(0);

        lawClientRepository.addLawClient(first);

        count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(1);
    }
    @Test
    void updateLawClient(){
        long count = lawClientRepository.LawClients.size();

        lawClientRepository.addLawClient(first);

        lawClientRepository.updateLawClient(first.getId(),new LawClient(1,"updated",null));

        assertThat(lawClientRepository.getLawClientByID(1).getName()).isEqualTo("updated");
    }



    @Test
    void getAllLawClients(){
        long count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(0);

        lawClientRepository.addLawClient(first);
        lawClientRepository.addLawClient(second);

        count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void getOneLawClientById(){

        lawClientRepository.addLawClient(first);

        Integer num = first.getId();

        LawClient founded = lawClientRepository.getLawClientByID(num);

        assertThat(founded.getName()).isEqualTo("test1");

    }

    @Test
    void deleteLawClientById(){

        lawClientRepository.addLawClient(first);

        int count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(1);

        lawClientRepository.deleteById(first.getId());

        count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(0);
    }


    @Test
    void deleteAllLawClientsId() {
        lawClientRepository.addLawClient(first);
        lawClientRepository.addLawClient(second);

        int count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(2);

        lawClientRepository.deleteAll();

        count = lawClientRepository.LawClients.size();

        assertThat(count).isEqualTo(0);

    }
}
