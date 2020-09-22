package com.thoughtworks.capability.gtb.resume.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.resume.component.Converter;
import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.EducationEntity;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import com.thoughtworks.capability.gtb.resume.exception.PersonIsNotExistException;
import com.thoughtworks.capability.gtb.resume.service.EducationService;
import jdk.nashorn.internal.runtime.ListAdapter;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
class EducationControllerTest {

    @MockBean
    private EducationService educationService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JacksonTester<Education> educationJacksonTester;

    @Autowired
    private JacksonTester<EducationEntity> educationEntityJacksonTester;

    private Education education;

    @BeforeEach
    public void beforeEach() {
        education = Education.builder()
                .personId(1)
                .title("title")
                .year("2020")
                .description("description")
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(educationService);
    }

    @Nested
    class GetPersonalEducationsById {

        @Nested
        class whenPersonIdExist {

            @Test
            public void shouldReturnPersonById() throws Exception {
                List<Education> educationList = new ArrayList<>();
                educationList.add(education);
                when(educationService.getPersonalEducations(1)).thenReturn(educationList);

                MockHttpServletResponse response = mockMvc.perform(get("/users/{id}/educations",1))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                ObjectMapper objectMapper = new ObjectMapper();
                assertThat(response.getContentAsString()).isEqualTo(objectMapper.writeValueAsString(educationList));

                verify(educationService,times(1)).getPersonalEducations(1);
            }
        }

        @Nested
        class whenPersonIdNotExist {

            @Test
            public void shouldThrowPersonIsNotExistException() throws Exception {
                when(educationService.getPersonalEducations(2)).thenThrow(new PersonIsNotExistException());

                mockMvc.perform(get("/users/{id}/educations",2))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message",containsString("person is not exist")));

                verify(educationService,times(1)).getPersonalEducations(2);
            }
        }
    }

    @Nested
    class AddPersonalEducations {

        private Education newEducation;

        @BeforeEach
        public void beforeEach() {
            newEducation = Education.builder()
                    .title("title")
                    .year("2020")
                    .description("description")
                    .build();
        }

        @Test
        void shouldAddEducationSuccessWhenPersonIsExistAndEducationISValid() throws Exception{
            PersonEntity personEntity = PersonEntity.builder().id(1).build();
            EducationEntity educationEntity = Converter.educationConvertToEducationEntity(newEducation,personEntity);
            when(educationService.addPersonalEducations(1,newEducation))
                    .thenReturn(educationEntity);

            MockHttpServletResponse response = mockMvc.perform(post("/users/{id}/educations",1)
                    .content(educationJacksonTester.write(newEducation).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

            verify(educationService,times(1)).addPersonalEducations(1,newEducation);
        }

        @Test
        void shouldReturnNotFoundWhenPersonIdNotExist() throws Exception {
            when(educationService.addPersonalEducations(2,newEducation)).thenThrow(new PersonIsNotExistException());

             mockMvc.perform(post("/users/{id}/educations",2)
                    .content(educationJacksonTester.write(newEducation).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                     .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message",containsString("person is not exist")));

            verify(educationService,times(1)).addPersonalEducations(2,newEducation);
        }

        @Test
        void shouldReturnBadRequestWhenEducationIsInvalid() throws Exception {
            Education inValidEducation = Education.builder()
                    .title("title")
                    .year(null)
                    .description("description")
                    .build();

            MockHttpServletResponse response = mockMvc.perform(post("/users/{id}/educations",1)
                    .content(educationJacksonTester.write(inValidEducation).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();

            assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }
    }
}