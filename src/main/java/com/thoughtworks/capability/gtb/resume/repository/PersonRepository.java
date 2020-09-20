package com.thoughtworks.capability.gtb.resume.repository;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

}
