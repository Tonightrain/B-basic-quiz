package com.thoughtworks.capability.gtb.resume.domian;

import com.thoughtworks.capability.gtb.resume.component.GlobalVariable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import static com.thoughtworks.capability.gtb.resume.component.ErrorMessage.*;
import static com.thoughtworks.capability.gtb.resume.component.GlobalVariable.*;

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
}
