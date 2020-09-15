package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.exception.EducationsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/users/{id}/educations")
    public ResponseEntity addPersonalEducations(@PathVariable long id, @RequestBody @Valid Education education) throws EducationsNotExistException {
        educationService.addPersonalEducations(id,education);
        return ResponseEntity.created(null).build();
    }
}
