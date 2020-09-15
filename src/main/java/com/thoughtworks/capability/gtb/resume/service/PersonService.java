package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person getPerson(long id) throws PersonIsNotExistException {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()){
            throw new PersonIsNotExistException("person is not exist");
        }
        return person.get();
    }
}
