package com.jitendra.graphql.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.graphql.Entity.Person;
import com.jitendra.graphql.Service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> persons() {
        return personService.getAllPersons();
    }

    @PostMapping("/persons")
    public Person savePerson(@RequestBody Person person) {
        return personService.savPerson(person);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        Long id = person.getId();
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null for update");
        }
        return personService.updatePerson(id, person);
    }

   

    @DeleteMapping("/persons/{id}")
    public String deletePersonById(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null for deletion");
        }
        return personService.deletePersonById(id);
    }

}
