package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:1234")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService){
        this.educationService = educationService;
    }

    @GetMapping("/users/{id}/educations")
    public List<Education> getPersonalEducations(@PathVariable long id) {
        return educationService.getPersonalEducations(id);
    }

    @PostMapping("/users/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPersonalEducations(@PathVariable long id, @RequestBody @Valid Education education) {
        educationService.addPersonalEducations(id,education);
    }
}
