package com.thoughtworks.capability.gtb.resume.repository;

import com.thoughtworks.capability.gtb.resume.domian.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
    private List<Person> personList;

    public PersonRepository(){
        this.personList = new ArrayList<>();
        Person person = Person.builder().id(1).name("Mike").age(18)
                .avatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600151405644&di=43f965b1e64995f714882f45f66c24e9&imgtype=0&src=http%3A%2F%2Fpic4.zhimg.com%2F50%2Fv2-d2a17325b24040679897d9410d1e2664_hd.jpg")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .build();
        personList.add(person);
    }

    public Optional<Person> findById(long id) {
        return personList.stream().filter(person -> person.getId() == id).findFirst();
    }

    public long addPerson(Person person) {
        person.setId(personList.size()+1);
        personList.add(person);
        return person.getId();
    }
}
