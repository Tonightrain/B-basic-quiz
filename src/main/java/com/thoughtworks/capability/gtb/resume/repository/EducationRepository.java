package com.thoughtworks.capability.gtb.resume.repository;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.EducationEntity;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Long> {

    @Query(nativeQuery = true, value = "select * from education where person_id = :personId")
    Optional<List<EducationEntity>> findAllByPerson(@Param("personId") Long personId);

}
