package com.thoughtworks.capability.gtb.resume.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    private long userId;
    private String year;
    private String title;
    private String description;
}
