package com.zhaomsdemo.research.parentship.business.service;

import com.zhaomsdemo.research.parentship.dao.document.Person;
import com.zhaomsdemo.research.parentship.web.dto.PersonDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PersonService {
    Person createPerson(PersonDto person);
    Person updatePerson(Person person);
    Person deletePerson(String id);
    Person getPerson(String id);
    List<Person> getPersons(PageRequest pageRequest);
}
