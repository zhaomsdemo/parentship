package com.zhaomsdemo.research.parentship.business.service;

import com.zhaomsdemo.research.parentship.business.exception.PersonNotFoundException;
import com.zhaomsdemo.research.parentship.dao.node.PersonNode;
import com.zhaomsdemo.research.parentship.dao.repository.PersonNodeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zhaomsdemo.research.parentship.constant.ErrorConstant.PERSON_ID_NOT_EXISTS;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PersonNodeServiceNodeImpl implements PersonNodeService {

    final PersonNodeRepository personNodeRepository;

    @Override
    public List<PersonNode> getAllPersonNodes() {
        return personNodeRepository.findAll();
    }

    @Override
    public PersonNode createPersonNode(PersonNode personNode) {
        return personNodeRepository.save(personNode);
    }

    @Override
    public PersonNode updatePersonNode(PersonNode personNode) {
        Long id = personNode.getId();
        if (personNodeRepository.existsById(id)) {
            return personNodeRepository.save(personNode);
        } else {
            throw new PersonNotFoundException(PERSON_ID_NOT_EXISTS + id);
        }
    }

    @Override
    public PersonNode getPersonNodeById(Long id) {
        return personNodeRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(PERSON_ID_NOT_EXISTS + id));
    }

    @Override
    public PersonNode deletePersonNode(Long id) {
        if (personNodeRepository.existsById(id)) {
            PersonNode personNode = personNodeRepository.findById(id).get();
            personNodeRepository.deleteById(id);
            return personNode;
        } else {
            throw new PersonNotFoundException(PERSON_ID_NOT_EXISTS + id);
        }
    }

    @Override
    public PersonNode addFriedPersonNode(PersonNode personNode, PersonNode friedPersonNode) {
        return null;
    }

    @Override
    public void deleteAll() {
        personNodeRepository.deleteAll();
    }
}
