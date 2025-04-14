package com.example.poc.event.rest.controller;


import com.example.poc.event.application.ports.incoming.ManagePersonUseCase;
import com.example.poc.event.domain.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ServicePocDeferController {
    private final ManagePersonUseCase propertyPersonApplicationService;
    private final PersonEventListener personEventListener;

    @RequestMapping(
            method = {RequestMethod.GET},
            value = {"/person/{personId}"},
            produces = {"application/json", "application/problem+json"}
    )
    public DeferredResult<PersonDto> getPersonByProperty(@PathVariable("personId") Long personId, @RequestParam("carteNumber") Long carteNumber) {
        log.debug("controller -> get Person for ID {}", personId);
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
