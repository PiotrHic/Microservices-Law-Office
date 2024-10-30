package com.example.lawclientservice.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LawClientTest {

    List<LawCase> lawCases = new ArrayList<>();

    LawClient lawClient = LawClient.builder()
            .id(1)
            .name("test")
            .lawCaseList(lawCases)
            .build();
    @Test
    public void setId(){
        //given
        Integer expected = 10;
        //when
        lawClient.setId(expected);
        //then
        Assertions.assertEquals(expected, lawClient.getId());
    }

    @Test
    public void getId(){
        //given
        Integer expected = 10;
        //when
        lawClient.setId(expected);
        //then
        Assertions.assertEquals(expected, lawClient.getId());
    }

    @Test
    public void setName(){
        //given
        String expected = "name";
        //when
        lawClient.setName(expected);
        //then
        Assertions.assertEquals(expected, lawClient.getName());
    }

    @Test
    public void getName(){
        //given
        String expected = "name";
        //when
        lawClient.setName(expected);
        //then
        Assertions.assertEquals(expected, lawClient.getName());
    }

    @Test
    public void setLawCases(){
        //given
        Integer expected = 1;
        //when
        lawCases.add(new LawCase());
        lawClient.setLawCaseList(lawCases);
        //then
        Assertions.assertEquals(expected, lawClient.getLawCaseList().size());
    }

    @Test
    public void getLawCases(){
        //given
        Integer expected = 1;
        //when
        lawCases.add(new LawCase());
        lawClient.setLawCaseList(lawCases);
        //then
        Assertions.assertEquals(expected, lawClient.getLawCaseList().size());
    }

}
