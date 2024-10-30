package com.example.lawclientservice;

import org.springframework.boot.SpringApplication;

public class TestLawclientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(LawclientServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
