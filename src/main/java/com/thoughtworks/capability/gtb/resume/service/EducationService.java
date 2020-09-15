package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.exception.EducationsNotExistException;
import com.thoughtworks.capability.gtb.resume.repository.EducationRepository;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository){
        this.educationRepository = educationRepository;
    }

    public List<Education> getPersonalEducations(long id) throws EducationsNotExistException {
        return educationRepository.findEducationByUserId(id);
    }

    public void addPersonalEducations(long id, Education education) throws EducationsNotExistException {
        educationRepository.addPersonalEducationsById(id,education);
    }
}
