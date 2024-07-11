package com.zhaomsdemo.research.parentship.web.controller;

import com.zhaomsdemo.research.parentship.business.service.PersonService;
import com.zhaomsdemo.research.parentship.dao.document.Person;
import com.zhaomsdemo.research.parentship.web.dto.PersonDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PersonController {

    final PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Person> persons = personService.getPersons(pageRequest);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findPerson(@PathVariable String id) {
        Person person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody PersonDto person) {
        Person newPerson = personService.createPerson(person);
        return ResponseEntity.ok(newPerson);
    }

    @PatchMapping("/person/{id}/father/{fatherId}")
    public ResponseEntity<Person> updateFather(@PathVariable String id, @PathVariable String fatherId) {
        return ResponseEntity.ok().build();
    }
}
