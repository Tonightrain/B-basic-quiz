package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.repository.EducationRepository;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    private final PersonRepository personRepository;

    public EducationService(EducationRepository educationRepository, PersonRepository personRepository){
        this.educationRepository = educationRepository;
        this.personRepository = personRepository;
    }

    public List<Education> getPersonalEducations(long id) {
        return educationRepository.findEducationByUserId(id);
    }

    public void addPersonalEducations(long id, Education education) throws PersonIsNotExistException {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()){
            throw new PersonIsNotExistException();
        }
        educationRepository.addPersonalEducationsById(id,education);
    }
}
