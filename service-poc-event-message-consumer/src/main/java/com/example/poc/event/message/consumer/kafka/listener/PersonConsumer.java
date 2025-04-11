package com.example.poc.event.message.consumer.kafka.listener;

import com.example.poc.event.application.service.PersonApplicationService;
import com.example.poc.event.domain.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonConsumer {

    private final PersonApplicationService personApplicationService;

    @KafkaListener(topics = "person-response", groupId = "event-group")
    @Transactional
    public void handleCbssResponse(PersonDto response) {
        // Forward to Application Layer (No event publishing here)
        personApplicationService.processPersonResponse(response);
    }
}
