package com.thoughtworks.capability.gtb.resume.entity;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.thoughtworks.capability.gtb.resume.component.ErrorMessage.*;
import static com.thoughtworks.capability.gtb.resume.component.GlobalVariable.*;
import static com.thoughtworks.capability.gtb.resume.component.GlobalVariable.DESCRIPTION_MAX_LENGTH;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "education")
public class EducationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    private String year;

    private String title;

    private String description;
}
