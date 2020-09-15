package com.thoughtworks.capability.gtb.resume.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    private long userId;

    @NotBlank(message = "year should not be null")
    private String year;

    @NotBlank(message = "title should not be null")
    @Size(min = 1,max = 256)
    private String title;

    @NotBlank(message = "description should not be null")
    @Size(min = 1,max = 4096)
    private String description;
}
