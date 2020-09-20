package com.thoughtworks.capability.gtb.resume.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.thoughtworks.capability.gtb.resume.component.GlobalVariable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import javax.validation.constraints.*;
import static com.thoughtworks.capability.gtb.resume.component.ErrorMessage.*;
import static com.thoughtworks.capability.gtb.resume.component.GlobalVariable.*;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private long id;

    @NotBlank(message = NAME_INVALID)
    @Size(min = GlobalVariable.NAME_MIN_LENGTH,max = NAME_MAX_LENGTH,message = NAME_INVALID)
    private String name;

    @NotNull(message = AGE_INVALID)
    @Min(value = MIN_AGE,message = AGE_IS_TOO_SMALL)
    private long age;

    @NotBlank(message = AVATAR_INVALID)
    @Size(min = AVATAR_MIN_LENGTH,max = AVATAR_MAX_LENGTH,message = AVATAR_INVALID)
    private String avatar;

    @Size(max = PERSON_DESCRIPTION_MAX_LENGTH,message = DESCRIPTION_INVALID)
    private String description;

    @JsonIgnore
    private List<Education> educationList;
}
