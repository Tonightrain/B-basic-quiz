package com.thoughtworks.capability.gtb.resume.entity;

import com.thoughtworks.capability.gtb.resume.component.GlobalVariable;
import com.thoughtworks.capability.gtb.resume.domian.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static com.thoughtworks.capability.gtb.resume.component.ErrorMessage.*;
import static com.thoughtworks.capability.gtb.resume.component.GlobalVariable.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private long age;

    private String avatar;

    private String description;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "personEntity")
    private List<EducationEntity> educationEntityList;
}
