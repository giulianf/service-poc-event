package com.example.poc.event.application.service;

import com.example.poc.event.domain.dto.PersonDto;
import org.springframework.context.ApplicationEvent;

public class PersonUpdatedEvent extends ApplicationEvent {
    private final String requestId;
    private final PersonDto data;

    public PersonUpdatedEvent(Object source, String requestId, PersonDto data) {
        super(source);
        this.requestId = requestId;
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public PersonDto getData() {
        return data;
    }
}
