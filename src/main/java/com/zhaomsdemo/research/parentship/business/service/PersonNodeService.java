package com.zhaomsdemo.research.parentship.business.service;

import com.zhaomsdemo.research.parentship.dao.node.PersonNode;

import java.util.List;

public interface PersonNodeService {

    List<PersonNode> getAllPersonNodes();
    PersonNode createPersonNode(PersonNode personNode);
    PersonNode updatePersonNode(PersonNode personNode);
    PersonNode getPersonNodeById(Long id);
    PersonNode deletePersonNode(Long id);
    PersonNode addFriedPersonNode(PersonNode personNode, PersonNode friedPersonNode);
    void deleteAll();
}
