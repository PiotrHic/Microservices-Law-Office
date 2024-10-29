package com.example.lawcaseservice;

import org.springframework.boot.SpringApplication;

public class TestLawcaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(LawcaseServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
