package com.example.poc.event.application.service;

import com.example.poc.event.PersonProducer;
import com.example.poc.event.PersonRequest;
import com.example.poc.event.application.ports.incoming.ManagePersonUseCase;
import com.example.poc.event.domain.dto.PersonDto;
import com.example.poc.event.domain.repository.PersonRepository;
import com.example.poc.event.domain.service.PersonDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PersonApplicationService implements ManagePersonUseCase {
    private final PersonDomainService personDomainService;
    private final ApplicationEventPublisher eventPublisher;
    private final PersonRepository personRepository;
    private final PersonProducer personProducer;

    public PersonDto getPerson(Long personId) {
        return personDomainService.getPerson(personId);
    }

    public void requestPersonFromCbss(Long personId, Long carteNb) {
        PersonRequest request = new PersonRequest(personId, carteNb, personId + "-" + carteNb);
        personProducer.send(request);
    }

    public PersonDto getPersonByCarteNb(Long carteNb) {
        return personRepository.findByCarteNb(carteNb);
//        return personDomainService.getInterlocutorsByProperty(personId, lang);
    }

    @Override
    public void processUpdateAddressInterlocutor(PersonDto response) {
        // address saved
//        personRepository.save();
    }

    public void processPersonResponse(PersonDto response) {
        // Store in Domain Layer (DB & Cache)
        personDomainService.processPerson(response);

        // Publish Event to Notify REST Module (`DeferredResult`)
        eventPublisher.publishEvent(new PersonUpdatedEvent(this, response.getId() + "-" + response.getCarteNb(), response));
    }

}
