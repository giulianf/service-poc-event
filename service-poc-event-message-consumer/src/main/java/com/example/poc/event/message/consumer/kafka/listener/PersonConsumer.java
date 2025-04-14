package com.example.poc.event.message.consumer.kafka.listener;

import com.example.poc.event.application.service.PersonApplicationService;
import com.example.poc.event.domain.dto.PersonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonConsumer {

    private final PersonApplicationService personApplicationService;

    @KafkaListener(topics = "person-response", groupId = "event-group")
    @Transactional
    public void handleCbssResponse(PersonDto response) throws JsonProcessingException {
        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            PersonDto response = objectMapper.readValue(message, PersonDto.class);
            personApplicationService.processPersonResponse(response);
        } catch (Exception e) {
            // Log and handle properly to avoid retry loop
//            log.error("Error processing message: {}", message, e);
            log.error("Error processing message: {}", response, e);
            throw e; // or don't throw if you want to skip
        }
    }

}
