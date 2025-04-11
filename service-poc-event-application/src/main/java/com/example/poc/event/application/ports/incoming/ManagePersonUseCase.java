package com.example.poc.event.application.ports.incoming;


import com.example.poc.event.domain.dto.PersonDto;

public interface ManagePersonUseCase {

    PersonDto getPerson(Long personId);

    void requestPersonFromCbss(Long personId, Long carteNb);

    PersonDto getPersonByCarteNb(Long carteNb);

    void processUpdateAddressInterlocutor(PersonDto response);
}
