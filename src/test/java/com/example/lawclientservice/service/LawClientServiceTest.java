package com.example.lawclientservice.service;


import com.example.lawclientservice.domain.LawClient;
import com.example.lawclientservice.repository.InMemoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LawClientServiceTest {

    @Mock
    InMemoryRepository repository;

    AutoCloseable autoCloseable;

    @InjectMocks
    LawClientServiceImpl lawClientService;

    LawClient returned= LawClient.builder()
            .id(1)
            .name("test1")
            .build();

    LawClient first = LawClient.builder()
            .id(2)
            .name("test2")
            .build();

    LawClient second = LawClient.builder()
            .id(3)
            .name("test3")
            .build();

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        lawClientService = new LawClientServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void createLawClient(){

        //stub the data
        when(repository.addLawClient(returned)).thenReturn(returned);

        LawClient result = lawClientService.createLawClient(returned);
        Assertions.assertEquals("test1", result.getName());
    }


    @Disabled
    @Test
    void updateLawClient(){


        //stub the data
        when(repository.getLawClientByID(first.getId())).thenReturn(first);
        when(repository.addLawClient(first)).thenReturn(first);

        lawClientService.createLawClient(first);

        LawClient result = lawClientService.updateLawClient(second.getId(), second);
        Assertions.assertEquals("test2", result.getName());

    }

    @Disabled
    @Test
    public void getOneLawClient() throws Exception{

        //stub the data
        when(repository.getLawClientByID(returned.getId())).thenReturn(returned);
        LawClient result = lawClientService.getLawClient(returned.getId());
        Assertions.assertEquals("test", result.getName());
    }
    @Test
    void getAllLawClients(){
        // when
        when(repository.getAllLawClients()).
                thenReturn(Arrays.asList(first,
                        second));

        //then
        List<LawClient> lawClients = lawClientService.getAllLawClients();
        Assertions.assertEquals(lawClients.size(), 2);
    }

    @Disabled
    @Test
    void deleteLawClientById(){

        when(repository.getLawClientByID(returned.getId())).thenReturn(returned);
        String result = lawClientService.deleteLawClientByID(returned.getId());
        Assertions.assertEquals(result, isNotNull());
    }
    @Disabled
    @Test
    void deleteAllLawClients(){

        when(repository.getLawClientByID(returned.getId())).thenReturn(returned);
        String result = lawClientService.deleteAllLawClients();
        Assertions.assertEquals(result, isNotNull());
    }
}
