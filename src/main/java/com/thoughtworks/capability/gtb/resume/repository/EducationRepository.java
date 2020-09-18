package com.thoughtworks.capability.gtb.resume.repository;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EducationRepository {
    private List<Education> educationList = new ArrayList<>();

    public EducationRepository(){
        Education education1 = Education.builder().userId(1).year("2005")
                .title("Secondary school specializing in artistic")
                .description("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")
                .build();
        Education education2 = Education.builder().userId(1).year("2009")
                .title("First level graduation in Graphic Design")
                .description("Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus quaerat quibusdam perferendis? Iusto, quibusdam asperiores unde repellat.")
                .build();
        educationList.add(education1);
        educationList.add(education2);
    }


    public List<Education> findEducationByUserId(long id) {
       List<Education> personEducations = educationList.stream()
               .filter(education -> education.getUserId() == id)
               .collect(Collectors.toList());
       if (personEducations.size() == 0){
           return null;
       }
       return personEducations;
    }

    public void addPersonalEducationsById(long id, Education education) {
        education.setUserId(id);
        educationList.add(education);
    }
}
