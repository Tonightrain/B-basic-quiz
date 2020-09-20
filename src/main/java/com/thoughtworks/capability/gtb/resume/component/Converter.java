package com.thoughtworks.capability.gtb.resume.component;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.entity.EducationEntity;
import com.thoughtworks.capability.gtb.resume.entity.PersonEntity;

public class Converter {

    public static PersonEntity personConvertToPersonEntity(Person person) {
        return PersonEntity.builder()
                .name(person.getName())
                .age(person.getAge())
                .avatar(person.getAvatar())
                .description(person.getDescription())
                .build();
    }

    public static Person personEntityConvertToPerson(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .age(personEntity.getAge())
                .avatar(personEntity.getAvatar())
                .description(personEntity.getDescription())
                .build();
    }

    public static EducationEntity educationConvertToEducationEntity(Education education, PersonEntity personEntity){
        return EducationEntity.builder()
                .personEntity(personEntity)
                .year(education.getYear())
                .title(education.getTitle())
                .description(education.getDescription())
                .build();
    }

    public static Education educationEntityConvertToEducation(EducationEntity educationEntity,Person person){
        return Education.builder()
                .personId(person.getId())
                .year(educationEntity.getYear())
                .title(educationEntity.getTitle())
                .description(educationEntity.getDescription())
                .build();
    }
}
