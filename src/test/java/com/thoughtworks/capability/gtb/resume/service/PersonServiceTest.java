package com.thoughtworks.capability.gtb.resume.service;

import com.thoughtworks.capability.gtb.resume.component.Converter;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import com.thoughtworks.capability.gtb.resume.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository);
        person = Person.builder()
                .id(1)
                .name("Mike")
                .age(20)
                .avatar("avatar")
                .description("description")
                .build();
    }

    @Nested
    class GetPerson {

        @Test
        void shouldReturnPersonWhenPersonIsExist() {
            long testId = 1;
            PersonEntity personEntity = Converter.personConvertToPersonEntity(person);

            when(personRepository.findById(testId)).thenReturn(Optional.of(personEntity));

            Person foundPerson = personService.getPerson(testId);

            verify(personRepository,times(1)).findById(testId);
        }

        @Test
        void shouldThrowExceptionWhenPersonIsNotExist() {
            long testId = 1;
            when(personRepository.findById(testId)).thenReturn(Optional.empty());

            PersonIsNotExistException thrownException = assertThrows(PersonIsNotExistException.class, () -> {
                personService.getPerson(testId);
            });

            assertThat(thrownException.getMessage()).containsSequence("person is not exist");
        }
    }

    @Nested
    class AddPerson {

        private Person newPerson;

        @BeforeEach
        void setUp() {
            newPerson = Person.builder()
                    .name("Jack")
                    .age(20)
                    .avatar("avatar")
                    .description("description")
                    .build();
        }

        @Test
        void shouldAddPerson() {
            PersonEntity personEntity = Converter.personConvertToPersonEntity(newPerson);

            when(personRepository.save(personEntity)).thenReturn(personEntity);

            personService.addPerson(newPerson);

            verify(personRepository,times(1)).save(personEntity);
        }
    }

}