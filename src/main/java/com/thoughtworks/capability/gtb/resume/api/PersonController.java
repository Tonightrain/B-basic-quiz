package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// GTB: @ResponseStatus 了解一下
@RestController
@CrossOrigin("http://localhost:1234")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getPerson(@PathVariable long id) throws PersonIsNotExistException {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @PostMapping("/users")
    public ResponseEntity addPerson(@RequestBody @Valid Person person){
        return ResponseEntity.created(null).body(personService.addPerson(person));
    }


}
