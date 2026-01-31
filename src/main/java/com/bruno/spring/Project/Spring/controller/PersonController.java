package com.bruno.spring.Project.Spring.controller;


import com.bruno.spring.Project.Spring.model.Person;
import com.bruno.spring.Project.Spring.services.PersonServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @GetMapping(value = "/{id}"
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable String id) {
        return personServices.findById(id);
    }

    @GetMapping(value = "/all"
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll() {
        return personServices.findAll();
    }

    @PostMapping(value = "/create"
            , produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Person creatingPerson(@RequestBody Person person) {
        return personServices.createPerson(person);
    }

    @PutMapping("/update")
    public Person updatingPerson(@RequestBody Person person) {
        return personServices.updatePerson(person);
    }

    @DeleteMapping(value = "/{id}"
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void deletePerson(@PathVariable String id) {
        personServices.deletePerson(id);
    }
}
