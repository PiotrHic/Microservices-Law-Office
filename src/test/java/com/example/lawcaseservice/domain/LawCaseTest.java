package com.example.lawcaseservice.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LawCaseTest {

    LawClient lawClient = LawClient.builder.build();


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
    public void setLawyerId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void getLawyerId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void setLawClientId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void getLawClientId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void setLawyer(){
        //given
        String expected = "name";
        //when
        lawyer.setName(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getName());
    }

    @Test
    public void getLawyer(){
        //given
        String expected = "name";
        //when
        lawyer.setName(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getName());
    }

    @Test
    public void setLawClient(){
        //given
        String expected = "name";
        //when
        lawyer.setName(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getName());
    }

    @Test
    public void getLawClient(){
        //given
        String expected = "name";
        //when
        lawyer.setName(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getName());
    }


}
