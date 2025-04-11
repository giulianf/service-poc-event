package com.example.poc.event.rest.controller;


import com.example.poc.event.application.ports.incoming.ManagePersonUseCase;
import com.example.poc.event.domain.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ServicePocDeferController {
    private final ManagePersonUseCase propertyPersonApplicationService;
    private final PersonEventListener personEventListener;

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/properties/{propertyId}/interlocutor"},
            produces = {"application/json", "application/problem+json"}
    )
    public DeferredResult<PersonDto> getPersonByProperty(Long personId, Long carteNumber) {
        log.debug("controller -> get interlocutor for property ID {}", personId);
        DeferredResult<PersonDto> deferredResult = new DeferredResult<>(10000L);
        String requestId = personId + "-" + carteNumber;
        // ✅ Register DeferredResult in Event Listener
        personEventListener.registerDeferredResult(requestId, deferredResult);

        // ✅ Step 1: Check Cache First
        PersonDto cachedData = propertyPersonApplicationService.getPerson(personId);
        if (cachedData != null) {
            deferredResult.setResult(cachedData);
            return deferredResult;
        }

        // ❌ Step 2: If Cache is Empty, Request from `cbss` via Kafka
        propertyPersonApplicationService.requestPersonFromCbss(personId, carteNumber);

        return deferredResult;
    }
}
