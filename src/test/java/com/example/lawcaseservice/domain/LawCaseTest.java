package com.example.lawcaseservice.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LawCaseTest {

    LawClient lawClient = LawClient.builder()
            .name("lc1")
            .build();

    Lawyer lawyer = Lawyer.builder()
            .id(1)
            .name("test")
            .build();

    LawCase first = LawCase.builder()
            .id(1)
            .name("test1")
            .lawyerId(1)
            .LawClientId(1)
            .lawClient(lawClient)
            .lawyer(lawyer)
            .build();

    LawCase second = LawCase.builder()
            .id(12)
            .name("test2")
            .lawyerId(2)
            .LawClientId(2)
            .lawClient(lawClient)
            .lawyer(lawyer)
            .build();
    @Test
    public void setId(){
        //given
        Integer expected = 10;
        //when
        first.setId(expected);
        //then
        Assertions.assertEquals(expected, first.getId());
    }

    @Test
    public void getId(){
        //given
        Integer expected = 10;
        //when
        first.setId(expected);
        //then
        Assertions.assertEquals(expected, first.getId());
    }

    @Test
    public void setName(){
        //given
        String expected = "name";
        //when
        first.setName(expected);
        //then
        Assertions.assertEquals(expected, first.getName());
    }

    @Test
    public void getName(){
        //given
        String expected = "name";
        //when
        first.setName(expected);
        //then
        Assertions.assertEquals(expected, first.getName());
    }

    @Test
    public void setLawyerId(){
        //given
        Integer expected = 10;
        //when
        first.setLawyerId(expected);
        //then
        Assertions.assertEquals(expected, first.getLawyerId());
    }

    @Test
    public void getLawyerId(){
        //given
        Integer expected = 10;
        //when
        first.setLawyerId(expected);
        //then
        Assertions.assertEquals(expected, first.getLawyerId());
    }

    @Test
    public void setLawClientId(){
        //given
        Integer expected = 10;
        //when
        first.setLawClientId(expected);
        //then
        Assertions.assertEquals(expected, first.getLawClientId());
    }

    @Test
    public void getLawClientId(){
        //given
        Integer expected = 10;
        //when
        first.setLawClientId(expected);
        //then
        Assertions.assertEquals(expected, first.getLawClientId());
    }

    @Test
    public void setLawyer(){
        //given
        Lawyer expected = null;
        //when
        first.setLawyer(expected);
        //then
        Assertions.assertEquals(expected, first.getLawyer());
    }

    @Test
    public void getLawyer(){
        //given
        Lawyer expected = null;
        //when
        first.setLawyer(expected);
        //then
        Assertions.assertEquals(expected, first.getLawyer());
    }

    @Test
    public void setLawClient(){
        //given
        LawClient expected = null;
        //when
        first.setLawClient(expected);
        //then
        Assertions.assertEquals(expected, first.getLawClient());
    }

    @Test
    public void getLawClient(){
        //given
        LawClient expected = null;
        //when
        first.setLawClient(expected);
        //then
        Assertions.assertEquals(expected, first.getLawClient());
    }


}
