package com.example.lawyerservice.service;

import com.example.lawyerservice.domain.Lawyer;
import com.example.lawyerservice.repository.InMemoryRepository;
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
public class LawyerServiceTest {

    @Mock
    InMemoryRepository repository;

    AutoCloseable autoCloseable;

    @InjectMocks
    LawyerServiceImpl lawyerService;

    Lawyer returned= Lawyer.builder()
            .id(1)
            .name("test1")
            .build();

    Lawyer first = Lawyer.builder()
            .id(2)
            .name("test2")
            .build();

    Lawyer second = Lawyer.builder()
            .id(3)
            .name("test3")
            .build();

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        lawyerService = new LawyerServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void createLawyer(){

        //stub the data
        when(repository.addLawyer(returned)).thenReturn(returned);

        Lawyer result = lawyerService.addLawyer(returned);
        Assertions.assertEquals("test1", result.getName());
    }


    @Disabled
    @Test
    void updateLawyer(){


        //stub the data
        when(repository.getLawyerByID(first.getId())).thenReturn(first);
        when(repository.addLawyer(first)).thenReturn(first);

        lawyerService.addLawyer(first);

        Lawyer result = lawyerService.updateLawyer(second.getId(), second);
        Assertions.assertEquals("test2", result.getName());

    }

    @Disabled
    @Test
    public void getOneLawyer() throws Exception{

        //stub the data
        when(repository.getLawyerByID(returned.getId())).thenReturn(returned);
        Lawyer result = lawyerService.getLawyerByID(returned.getId());
        Assertions.assertEquals("test", result.getName());
    }
    @Test
    void getAllLawyers(){
        // when
        when(repository.getAllLawyers()).
                thenReturn(Arrays.asList(first,
                        second));

        //then
        List<Lawyer> lawyers = lawyerService.getAllLawyers();
        Assertions.assertEquals(lawyers.size(), 2);
    }

    @Disabled
    @Test
    void deleteLawyerById(){

        when(repository.getLawyerByID(returned.getId())).thenReturn(returned);
        String result = lawyerService.deleteById(returned.getId());
        Assertions.assertEquals(result, isNotNull());
    }
    @Disabled
    @Test
    void deleteAllLawyers(){

        when(repository.getLawyerByID(returned.getId())).thenReturn(returned);
        String result = lawyerService.deleteAll();
        Assertions.assertEquals(result, isNotNull());
    }
}
