package com.zhaomsdemo.research.parentship.dao.repository;

import com.zhaomsdemo.research.parentship.dao.document.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    Page<Person> findAllBy(Pageable pageable);
}
