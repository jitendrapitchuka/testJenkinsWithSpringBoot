package com.jitendra.graphql.Resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.jitendra.graphql.Entity.Person;
import com.jitendra.graphql.Service.PersonService;

@Controller
public class PersonResolver {

    @Autowired
    private PersonService personService;

    @QueryMapping
    public List<Person> persons() {
        return personService.getAllPersons();
    }

    @MutationMapping
    public Person savePerson(@Argument String name, @Argument int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return personService.savPerson(person);
    }

    @MutationMapping
    public Person updatePerson(@Argument Long id, @Argument String name, @Argument int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return personService.updatePerson(id, person);
    }

    @QueryMapping
    public Person personById(@Argument Long id) {
        return personService.getPersonById(id);
    }

    @MutationMapping
    public String deletePersonById(@Argument Long id) {
        return personService.deletePersonById(id);
    }

}
