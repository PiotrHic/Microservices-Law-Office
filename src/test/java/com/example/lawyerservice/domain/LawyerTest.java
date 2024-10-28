package com.example.lawyerservice.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LawyerTest {

    List<LawCase> lawCases = new ArrayList<>();

    Lawyer lawyer = Lawyer.builder()
            .id(1)
            .name("test")
            .lawCaseList(lawCases)
            .build();
    @Test
    public void setId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void getId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void setName(){
        //given
        String expected = "name";
        //when
        lawyer.setName(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getName());
    }

    @Test
    public void getName(){
        //given
        String expected = "name";
        //when
        lawyer.setName(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getName());
    }

    @Test
    public void setLawCases(){
        //given
        Integer expected = 1;
        //when
        lawCases.add(new LawCase());
        lawyer.setLawCaseList(lawCases);
        //then
        Assertions.assertEquals(expected, lawyer.getLawCaseList().size());
    }

    @Test
    public void getLawCases(){
        //given
        Integer expected = 1;
        //when
        lawCases.add(new LawCase());
        lawyer.setLawCaseList(lawCases);
        //then
        Assertions.assertEquals(expected, lawyer.getLawCaseList().size());
    }

}
