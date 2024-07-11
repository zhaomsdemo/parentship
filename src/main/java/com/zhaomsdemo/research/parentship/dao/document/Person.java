package com.zhaomsdemo.research.parentship.dao.document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhaomsdemo.research.parentship.constant.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "personNode")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"siblings"})
public class Person {

    @MongoId
    String id;
    String name;
    Gender gender;
    Date birthday;
    Address address1;
    Address address2;
    @DBRef(lazy = true)
    Person father;
    @DBRef(lazy = true)
    Person mother;
    @DBRef(lazy = true)
    @JsonBackReference
    List<Person> children;

    public void addChild(Person child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public void removeChild(Person child) {
        if (children != null) {
            children.remove(child);
        }
    }

    public List<Person> getSiblings() {
        if (father != null) {
            return father.getChildren().stream().filter(personNode -> !this.id.equals(personNode.getId())).toList();
        }
        return new ArrayList<>();
    }
}
