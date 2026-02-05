package com.bruno.spring.Project.Spring.controller;


import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.services.PersonServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable Long id) {
        return personServices.findById(id);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll() {
        return personServices.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO creatingPerson(@RequestBody PersonDTO person) {
        return personServices.createPerson(person);
    }

    @PutMapping("/update")
    public PersonDTO updatingPerson(@RequestBody PersonDTO person) {
        return personServices.updatePerson(person);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePerson(@PathVariable Long id) {
        personServices.deletePerson(id);
    }
}