package com.example.poc.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonProducer {

    private final KafkaTemplate<String, PersonRequest> kafkaTemplate;

    public void send(PersonRequest request) {
        kafkaTemplate.send("person-request", request.getRequestId(), request);
    }
}
