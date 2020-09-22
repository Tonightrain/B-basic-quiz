package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:1234")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/users/{id}")
    public Person getPerson(@PathVariable long id) {
        return personService.getPerson(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public long addPerson(@RequestBody @Valid Person person){
        return personService.addPerson(person);
    }
}
