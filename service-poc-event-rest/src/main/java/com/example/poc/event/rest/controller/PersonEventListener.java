package com.example.poc.event.rest.controller;

import com.example.poc.event.application.service.PersonUpdatedEvent;
import com.example.poc.event.domain.dto.PersonDto;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PersonEventListener {

    private final ConcurrentHashMap<String, DeferredResult<PersonDto>> pendingRequests;

    public PersonEventListener() {
        this.pendingRequests = new ConcurrentHashMap<>();
    }

    public void registerDeferredResult(String requestId, DeferredResult<PersonDto> deferredResult) {
        pendingRequests.put(requestId, deferredResult);
    }

    @EventListener
    public void handlePersonUpdatedEvent(PersonUpdatedEvent event) {
        DeferredResult<PersonDto> deferredResult = pendingRequests.remove(event.getRequestId());
        if (deferredResult != null) {
            deferredResult.setResult(event.getData());
        }
    }
}
