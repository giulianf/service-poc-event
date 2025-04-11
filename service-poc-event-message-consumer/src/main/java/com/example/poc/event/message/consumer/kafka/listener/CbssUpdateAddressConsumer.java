package com.example.poc.event.message.consumer.kafka.listener;

import com.example.poc.event.application.service.PersonApplicationService;
import com.example.poc.event.domain.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CbssUpdateAddressConsumer {

    private final PersonApplicationService personApplicationService;

    @KafkaListener(topics = "update-address", groupId = "event-group")
    @Transactional
    public void handleCbssResponse(PersonDto response) {
        // ✅ Forward to Application Layer (No event publishing here)
        personApplicationService.processUpdateAddressInterlocutor(response);
    }
}
