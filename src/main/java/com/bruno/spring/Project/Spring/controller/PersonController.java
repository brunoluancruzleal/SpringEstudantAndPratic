package com.bruno.spring.Project.Spring.controller;


import com.bruno.spring.Project.Spring.controller.doc.PersonControllerDoc;
import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.services.PersonServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
public class PersonController implements PersonControllerDoc {

    private final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @Override
    public PersonDTO findById(@PathVariable Long id) {
        PersonDTO person = personServices.findById(id);
        hateosLink(person);
        return person;
    }

    @Override
    public List<PersonDTO> findAll() {
        var all = personServices.findAll();
        all.forEach(this::hateosLink);
        return all;
    }

    @Override
    public PersonDTO creatingPerson(@RequestBody PersonDTO person) {

        var createdPerson = personServices.createPerson(person);
        hateosLink(createdPerson);
        return createdPerson;
    }


    @Override
    public PersonDTO updatingPerson(@RequestBody PersonDTO person) {
        var updatedPerson = personServices.updatePerson(person);
        hateosLink(updatedPerson);
        return updatedPerson;
    }

    @Override
    public void deletePerson(@PathVariable Long id) {

        personServices.deletePerson(id);
    }


    private void hateosLink(PersonDTO person) {
        person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("all-persons"));
        person.add(linkTo(methodOn(PersonController.class).updatingPerson(person)).withRel("update-person"));
        person.add(linkTo(methodOn(PersonController.class).creatingPerson(person)).withRel("create-person"));
 //       person.add(linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete-person").withType("Delete"));
    }

}