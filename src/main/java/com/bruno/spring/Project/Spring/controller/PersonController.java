package com.bruno.spring.Project.Spring.controller;


import com.bruno.spring.Project.Spring.controller.doc.PersonControllerDoc;
import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.data.dto.PersonPatchDTO;
import com.bruno.spring.Project.Spring.services.PersonServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<PersonDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "deriction", defaultValue = "asc") String direction

    ) {
        var SortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageablev = PageRequest.of(page,size,Sort.by(SortDirection, "name"));
        return ResponseEntity.ok(personServices.findAll(pageablev));

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


    @PatchMapping("/{id}")
    public ResponseEntity<PersonDTO> updateParcialPerson(@PathVariable Long id, @RequestBody PersonPatchDTO dto) {
        return ResponseEntity.ok(personServices.parciaUpdatePerson(id,dto));
    }

    private void hateosLink(PersonDTO person) {
        person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        person.add(linkTo(methodOn(PersonController.class).findAll(1,12,"asc")).withRel("all-persons"));
        person.add(linkTo(methodOn(PersonController.class).updatingPerson(person)).withRel("update-person"));
        person.add(linkTo(methodOn(PersonController.class).creatingPerson(person)).withRel("create-person"));
 //       person.add(linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete-person").withType("Delete"));
    }

}