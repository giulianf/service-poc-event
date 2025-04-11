package com.example.poc.event.domain.repository;

import com.example.poc.event.domain.dto.PersonDto;

public interface PersonRepository {

    PersonDto findByCarteNb(Long carteNb);
}
