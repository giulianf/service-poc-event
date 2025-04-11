package com.example.poc.event.domain.service;

import com.example.poc.event.domain.dto.PersonDto;
import com.example.poc.event.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDomainService {

    private final PersonRepository personRepository;

    /**
     * ✅ Fetch PersonDto from Cache or DB
     */
    @Cacheable(value = "personCache", key = "#carteNb", unless = "#result == null")
    public PersonDto getPerson(Long carteNb) {
//        return personRepository.findBycarteNbAndLang(propertyId, lang);
        return null;
    }

    /**
     * ✅ Process Kafka Response & Store in DB
     */
    @Cacheable(value = "personCache", key = "#response.carteNb + '-' + #response.name")
    public void processPerson(PersonDto response) {
//        interlocutorRepository.save(response);
    }

}
