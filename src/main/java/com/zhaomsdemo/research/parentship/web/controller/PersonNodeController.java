package com.zhaomsdemo.research.parentship.web.controller;

import com.zhaomsdemo.research.parentship.business.service.PersonNodeService;
import com.zhaomsdemo.research.parentship.dao.node.PersonNode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class PersonNodeController {

    final PersonNodeService personNodeService;

    @PostMapping("/personNode")
    public ResponseEntity<PersonNode> createPersonNode(@RequestBody PersonNode personNode) {
        PersonNode newPersonNode = personNodeService.createPersonNode(personNode);
        return ResponseEntity.ok(newPersonNode);
    }

    @GetMapping("/personNodes")
    public ResponseEntity<List<PersonNode>> getPersonNodes() {
        List<PersonNode> personNodes = personNodeService.getAllPersonNodes();
        return ResponseEntity.ok(personNodes);
    }

    @GetMapping("/personNode/{id}")
    public ResponseEntity<PersonNode> findPersonNodeById(@PathVariable Long id) {
        PersonNode personNode = personNodeService.getPersonNodeById(id);
        return ResponseEntity.ok(personNode);
    }

    @DeleteMapping("/personNode/{id}")
    public ResponseEntity<PersonNode> deletePersonNodeById(@PathVariable Long id) {
        PersonNode personNode = personNodeService.deletePersonNode(id);
        return ResponseEntity.ok(personNode);
    }

    @DeleteMapping("/personNodes")
    public ResponseEntity<Void> deleteAllPersonNodes() {
        personNodeService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/personNode/friend")
    public ResponseEntity<PersonNode> addFriend(@RequestParam Long personId, @RequestParam Long friendId) {
        PersonNode personNode = personNodeService.getPersonNodeById(personId);
        PersonNode friendNode = personNodeService.getPersonNodeById(friendId);
        personNode.getFriends().add(friendNode);
        personNodeService.updatePersonNode(personNode);

        return ResponseEntity.ok(personNode);
    }
}
