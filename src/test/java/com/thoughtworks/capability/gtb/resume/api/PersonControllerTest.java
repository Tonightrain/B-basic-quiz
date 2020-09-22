package com.thoughtworks.capability.gtb.resume.api;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
@AutoConfigureJsonTesters
class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Person> personJacksonTester;

    private Person person;

    @BeforeEach
    public void beforeEach() {
        person = Person.builder()
                .id(1)
                .name("Mike")
                .age(18)
                .avatar("avatar")
                .description("description")
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(personService);
    }

    @Nested
    class GetPersonById {

        @Nested
        class whenPersonIdExist {

            @Test
            public void shouldReturnPersonById() throws Exception {
                when(personService.getPerson(1)).thenReturn(person);

                MockHttpServletResponse response = mockMvc.perform(get("/users/{id}",1))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                assertThat(response.getContentAsString()).isEqualTo(personJacksonTester.write(person).getJson());

                verify(personService,times(1)).getPerson(1);
            }
        }

        @Nested
        class whenPersonIdNotExist {

            @Test
            public void shouldThrowPersonIsNotExistException() throws Exception {
                when(personService.getPerson(2)).thenThrow(new PersonIsNotExistException());

                mockMvc.perform(get("/users/{id}",2))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message",containsString("person is not exist")));

                verify(personService,times(1)).getPerson(2);
            }
        }
    }

    @Nested
    class AddPerson {

        private Person newPerson;

        @BeforeEach
        public void beforeEach() {
            newPerson = Person.builder()
                    .name("Jack")
                    .age(20)
                    .avatar("Jack's avatar")
                    .description("Jack's description")
                    .build();
        }

        @Nested
        class whenPersonIsValid {

            @Test
            void shouldCreateNewPersonAndReturnItsId() throws Exception {
                when(personService.addPerson(newPerson)).thenReturn(5L);

                MockHttpServletRequestBuilder requestBuilder = post("/users")
                       .content(personJacksonTester.write(newPerson).getJson())
                       .contentType(MediaType.APPLICATION_JSON);

                MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                       .andReturn()
                       .getResponse();

                assertThat(response.getContentAsString()).isEqualTo("5");
                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

                verify(personService,times(1)).addPerson(newPerson);
            }
        }

        @Nested
        class whenPersonIsInValid {

            @Test
            void shouldReturnBadRequestWhenPersonIsInvalid() throws Exception {
                Person personNameIsBlank = Person.builder()
                        .name(null)
                        .age(18)
                        .avatar("avatar")
                        .description("description")
                        .build();

                MockHttpServletResponse response = mockMvc.perform(post("/users")
                        .content(personJacksonTester.write(personNameIsBlank).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            }
        }
    }
}