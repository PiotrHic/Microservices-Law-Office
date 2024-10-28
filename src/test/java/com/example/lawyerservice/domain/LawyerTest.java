package com.example.lawyerservice.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LawyerTest {

    Lawyer lawyer = Lawyer.builder()
            .id(1)
            .name("test").build();
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
    public void getId(){
        //given
        Integer expected = 10;
        //when
        lawyer.setId(expected);
        //then
        Assertions.assertEquals(expected, lawyer.getId());
    }

    @Test
    public void setLawCases(){
        //given
        String expected = null;
        //when
        lawyer.setLawCases(expected);
        //then
        Assertions.assertEquals(expected.size(), lawyer.getLawCases.size());
    }

    @Test
    public void getLawCases(){
        //given
        String expected = null;
        //when
        lawyer.setLawCases(expected);
        //then
        Assertions.assertEquals(expected.size(), lawyer.getLawCases.size());
    }

}
