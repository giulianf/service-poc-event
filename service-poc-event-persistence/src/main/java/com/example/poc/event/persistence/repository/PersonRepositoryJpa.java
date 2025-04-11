package com.example.poc.event.persistence.repository;

import com.example.poc.event.domain.dto.PersonDto;
import com.example.poc.event.domain.model.PersonEntity;
import com.example.poc.event.domain.repository.PersonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryJpa extends PersonRepository, JpaRepository<PersonEntity, Long>,
        QuerydslPredicateExecutor<PersonEntity> {

    @Query("select new com.example.poc.event.domain.dto.PersonDto(i.id, i.name, i.carteNb) from PersonEntity i where i.id = :propertyId ")
    PersonDto findByCarteNb(Long personId);

}
