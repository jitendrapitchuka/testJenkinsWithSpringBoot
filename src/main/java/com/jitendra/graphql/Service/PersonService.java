package com.jitendra.graphql.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.graphql.Entity.Person;
import com.jitendra.graphql.Repo.PersonRepo;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public Person savPerson(Person person) {
        return personRepo.save(person);
    }

    public Person getPersonById(Long id) {
        return personRepo.findById(id).orElseThrow(()->new RuntimeException("Person not found with id: " + id));
    }

    public String deletePersonById(Long id) {
        personRepo.deleteById(id);
        return "Person deleted with id: " + id;
    }

    public Person updatePerson(Long id, Person person) {
        Person existingPerson = personRepo.findById(id).orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        existingPerson.setName(person.getName());
        existingPerson.setAge(person.getAge());
        return personRepo.save(existingPerson);
    }

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }


}
