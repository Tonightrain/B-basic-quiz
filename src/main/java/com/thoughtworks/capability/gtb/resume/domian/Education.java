package com.thoughtworks.capability.gtb.resume.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.thoughtworks.capability.gtb.resume.component.ErrorMessage.*;
import static com.thoughtworks.capability.gtb.resume.component.GlobalVariable.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    private long userId;

    @NotBlank(message = YEAR_INVALID)
    private String year;

    @NotBlank(message = TITLE_INVALID)
    @Size(min = TITLE_MIN_LENGTH,max = TITLE_MAX_LENGTH)
    private String title;

    @NotBlank(message = EDUCATION_DESCRIPTION_INVALID)
    @Size(min = DESCRIPTION_MIN_LENGTH,max = DESCRIPTION_MAX_LENGTH)
    private String description;
}
