package com.thoughtworks.capability.gtb.resume.repository;

import com.thoughtworks.capability.gtb.resume.domian.Education;
import com.thoughtworks.capability.gtb.resume.domian.Person;
import com.thoughtworks.capability.gtb.resume.exception.EducationsNotExistException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EducationRepository {
    // GTB: - 这种情况，通常直接在声明处进行初始化
    private List<Education> educationList;

    public EducationRepository(){
        this.educationList = new ArrayList<>();
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


    public List<Education> findEducationByUserId(long id) throws EducationsNotExistException {
        // GTB: - 这里的缩进怎么都不对？
       List<Education> personEducations = educationList.stream().filter(education -> education.getUserId() == id).collect(Collectors.toList());
        // GTB: - 如果 user 就是刚刚好没有任何 educations 呢？也要抛异常？
        // GTB: 为空时就返回 [] 就行了，不需要抛异常
        if (personEducations.size() == 0){
            throw new EducationsNotExistException("The person’s education does not exist");
       }
       return personEducations;
    }

    public void addPersonalEducationsById(long id, Education education) throws EducationsNotExistException {
        List<Education> personEducations = educationList.stream().filter(education1 -> education1.getUserId() == id).collect(Collectors.toList());
        // GTB: - 新创建用户也不能增加 education？自己做功能测试要做全面！
        if (personEducations.size() == 0){
            throw new EducationsNotExistException("The person’s education does not exist");
        }
        education.setUserId(id);
        educationList.add(education);
    }
}
