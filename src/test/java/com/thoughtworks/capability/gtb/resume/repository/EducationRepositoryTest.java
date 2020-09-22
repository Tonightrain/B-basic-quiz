package com.thoughtworks.capability.gtb.resume.repository;

import com.thoughtworks.capability.gtb.resume.entity.EducationEntity;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldReturnEducationEntityListWhenFindByPersonEntity() {
        PersonEntity personEntity = PersonEntity.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
        entityManager.persistAndFlush(personEntity);

        EducationEntity educationEntity = EducationEntity.builder()
                .personEntity(personEntity)
                .year("2020")
                .title("title")
                .description("description")
                .build();
        entityManager.persistAndFlush(educationEntity);

        List<EducationEntity> list = educationRepository.findByPersonEntity(personEntity);

        assertThat(list).isEqualTo(Arrays.asList(educationEntity));
    }

}