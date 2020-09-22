package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.component.Converter;
import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.EducationEntity;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.repository.EducationRepository;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.capability.gtb.resume.component.Converter.personEntityConvertToPerson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EducationServiceTest {
    private EducationService educationService;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private PersonRepository personRepository;

    private Education education;
    private Person person;
    private PersonEntity personEntity;
    private EducationEntity educationEntity;

    @BeforeEach
    void setUp() {
        educationService = new EducationService(educationRepository,personRepository);
        education = Education.builder()
                .title("title")
                .year("2020")
                .description("description")
                .build();

        person = Person.builder()
                .id(1)
                .name("Mike")
                .age(20)
                .avatar("avatar")
                .description("description")
                .build();

       personEntity = Converter.personConvertToPersonEntity(person);

       educationEntity = Converter.educationConvertToEducationEntity(education,personEntity);
    }

    @Nested
    class GetPersonalEducations {

        @Test
        void shouldReturnPersonalEducationsWhenPersonIsExist() {
            long testId = 1;
            List<EducationEntity> educationEntities = new ArrayList<>();
            educationEntities.add(educationEntity);

            when(personRepository.findById(testId)).thenReturn(Optional.of(personEntity));
            when(educationRepository.findByPersonEntity(personEntity)).thenReturn(educationEntities);

            List<Education> list = educationService.getPersonalEducations(testId);

            verify(personRepository,times(1)).findById(testId);
            verify(educationRepository,times(1)).findByPersonEntity(personEntity);
            assertThat(list).isEqualTo(Arrays.asList(education));
        }

        @Test
        void shouldThrowExceptionWhenPersonIsNotExist() {
            long testId = 1;
            when(personRepository.findById(testId)).thenThrow(new PersonIsNotExistException());

            PersonIsNotExistException thrownException = assertThrows(PersonIsNotExistException.class, () -> {
                personRepository.findById(testId);
            });

            assertThat(thrownException.getMessage()).containsSequence("person is not exist");
        }
    }

    @Nested
    class AddPersonalEducations {

        @Test
        void shouldAddPersonalEducations() {
            when(personRepository.findById(1L)).thenReturn(Optional.of(personEntity));
            when(educationRepository.save(educationEntity)).thenReturn(educationEntity);

            educationService.addPersonalEducations(1,education);

            verify(educationRepository,times(1)).save(educationEntity);
        }
    }

}