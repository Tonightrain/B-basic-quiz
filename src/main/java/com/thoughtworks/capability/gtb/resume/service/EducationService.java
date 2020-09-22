package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.component.Converter;
import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.EducationEntity;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.repository.EducationRepository;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    private final PersonRepository personRepository;

    public EducationService(EducationRepository educationRepository, PersonRepository personRepository){
        this.educationRepository = educationRepository;
        this.personRepository = personRepository;
    }

    public List<Education> getPersonalEducations(long personId) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(PersonIsNotExistException::new);
        Person person = Converter.personEntityConvertToPerson(personEntity);
        List<EducationEntity> educationEntities = educationRepository.findByPersonEntity(personEntity);
        List<Education> educations = educationEntities.stream()
                .map(educationEntity -> Converter.educationEntityConvertToEducation(educationEntity,person))
                .collect(Collectors.toList());
        return educations;
    }

    public void addPersonalEducations(long id, Education education){
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(PersonIsNotExistException::new);
        EducationEntity educationEntity = Converter.educationConvertToEducationEntity(education,personEntity);
        educationRepository.save(educationEntity);
    }
}
