package com.example.poc.event.rest.controller;


import com.example.poc.event.application.ports.incoming.ManagePersonUseCase;
import com.example.poc.event.domain.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/person")
public class ServicePocEventController {
    private final ManagePersonUseCase managePersonUseCase;

    @GetMapping
    public ResponseEntity<PersonDto> requestPerson(@RequestParam Long personId, @RequestParam Long lang) {
        PersonDto response = managePersonUseCase.getPersonByCarteNb(personId);

        return ResponseEntity.ok(response);
    }
}
