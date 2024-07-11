package com.zhaomsdemo.research.parentship.dao.node;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node(value = "personNode")
public class PersonNode {

    @Id
    @GeneratedValue
    Long id;
    String name;
    Integer age;
    String gender;
    @Relationship(type = "IS_FRIEND", direction = Relationship.Direction.OUTGOING)
    Set<PersonNode> friends = new HashSet<>();
}
