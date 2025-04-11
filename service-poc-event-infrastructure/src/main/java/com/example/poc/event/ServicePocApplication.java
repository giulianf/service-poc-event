package com.example.poc.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@SpringBootApplication
public class ServicePocApplication {

    public static void main(String[] args) {
        log.info("Starting poc service poc-service-template...");
        run(ServicePocApplication.class, args);
    }

}
