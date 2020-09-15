package com.thoughtworks.capability.gtb.resume.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private long id;
    private String name;
    private long age;
    private String avatar;
    private String description;
}
