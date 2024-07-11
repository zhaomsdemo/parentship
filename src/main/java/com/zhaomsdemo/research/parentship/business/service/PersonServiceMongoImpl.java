package com.zhaomsdemo.research.parentship.business.service;

import com.zhaomsdemo.research.parentship.business.exception.InvalidOperationException;
import com.zhaomsdemo.research.parentship.business.exception.PersonNotFoundException;
import com.zhaomsdemo.research.parentship.dao.document.Person;
import com.zhaomsdemo.research.parentship.dao.repository.PersonRepository;
import com.zhaomsdemo.research.parentship.web.dto.PersonDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.zhaomsdemo.research.parentship.constant.ErrorConstant.NULL_OR_EMPTY_ID;
import static com.zhaomsdemo.research.parentship.constant.ErrorConstant.PERSON_ID_NOT_EXISTS;
import static java.util.Optional.ofNullable;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PersonServiceMongoImpl implements PersonService{

    final PersonRepository personRepository;

    @Override
    public Person createPerson(PersonDto person) {
        Person personNode = Person.builder()
                .name(person.getName())
                .gender(person.getGender())
                .birthday(person.getBirthday())
                .address1(person.getAddress1())
                .address2(person.getAddress2())
                .build();
        personRepository.save(personNode);
        addParentShip(personNode, person.getFather(), person.getMother());
        return personRepository.save(personNode);
    }

    @Override
    public Person updatePerson(Person person) {
        String id = person.getId();
        if (!StringUtils.hasText(id)) {
            throw new InvalidOperationException(NULL_OR_EMPTY_ID);
        }
        if (personRepository.existsById(id)) {
            ofNullable(person.getName()).ifPresent(person::setName);
            ofNullable(person.getBirthday()).ifPresent(person::setBirthday);
            ofNullable(person.getGender()).ifPresent(person::setGender);
            ofNullable(person.getAddress1()).ifPresent(person::setAddress1);
            ofNullable(person.getAddress2()).ifPresent(person::setAddress2);
            ofNullable(person.getFather()).ifPresent(person::setFather);
            ofNullable(person.getMother()).ifPresent(person::setMother);
            ofNullable(person.getChildren()).ifPresent(person::setChildren);
            return personRepository.save(person);
        } else {
            throw new PersonNotFoundException(PERSON_ID_NOT_EXISTS + id);
        }
    }

    @Override
    public Person deletePerson(String id) {
        if (!StringUtils.hasText(id)) {
            throw new InvalidOperationException(NULL_OR_EMPTY_ID);
        }
        if (personRepository.existsById(id)) {
            Person person = personRepository.findById(id).get();
            personRepository.deleteById(id);
            return person;
        } else {
            throw new PersonNotFoundException(PERSON_ID_NOT_EXISTS + id);
        }
    }

    @Override
    public Person getPerson(String id) {
        if (!StringUtils.hasText(id)) {
            throw new InvalidOperationException(NULL_OR_EMPTY_ID);
        }
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(PERSON_ID_NOT_EXISTS + id));
    }

    @Override
    public List<Person> getPersons(PageRequest pageRequest) {
        Page<Person> page = personRepository.findAllBy(pageRequest);
        return page.getContent();
    }

    private void addParentShip(Person person, String fatherId, String motherId) {
        if (StringUtils.hasText(fatherId)) {
            Person fatherNode = personRepository.findById(fatherId)
                    .orElseThrow(() -> new PersonNotFoundException(PERSON_ID_NOT_EXISTS + fatherId));
            fatherNode.addChild(person);
            person.setFather(fatherNode);
            personRepository.save(fatherNode);
        }
        if (StringUtils.hasText(motherId)) {
            Person motherNode = personRepository.findById(motherId)
                    .orElseThrow(() -> new PersonNotFoundException(PERSON_ID_NOT_EXISTS + motherId));
            motherNode.addChild(person);
            person.setMother(motherNode);
            personRepository.save(motherNode);
        }
    }
}
