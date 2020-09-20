package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.component.Converter;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person getPerson(long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(PersonIsNotExistException::new);
        Person person = Converter.personEntityConvertToPerson(personEntity);
        return person;
    }

    public PersonEntity addPerson(Person person) {
        PersonEntity personEntity = Converter.personConvertToPersonEntity(person);
        return personRepository.save(personEntity);
    }
}
