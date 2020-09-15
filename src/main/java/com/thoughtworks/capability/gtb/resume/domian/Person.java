package com.thoughtworks.capability.gtb.resume.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private long id;

    @NotBlank(message = "name should not be null")
    @Size(min = 1,max = 128)
    private String name;

    @NotNull(message = "age should not be null")
    @Min(value = 16,message = "age should be greater than 16")
    private long age;

    @NotBlank(message = "avatar should not be null")
    @Size(min = 8,max = 512)
    private String avatar;

    @Size(max = 1024)
    private String description;
}
