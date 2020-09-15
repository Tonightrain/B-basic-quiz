package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.exception.EducationsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:1234")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService){
        this.educationService = educationService;
    }

    @GetMapping("/users/{id}/educations")
    public ResponseEntity getPersonalEducations(@PathVariable long id) throws EducationsNotExistException {
        return ResponseEntity.ok(educationService.getPersonalEducations(id));
    }
}
