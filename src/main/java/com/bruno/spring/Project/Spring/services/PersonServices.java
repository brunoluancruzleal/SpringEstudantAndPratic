package com.bruno.spring.Project.Spring.services;


import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.exceptions.ResourceNotFoudException;
import com.bruno.spring.Project.Spring.model.Person;
import com.bruno.spring.Project.Spring.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.bruno.spring.Project.Spring.mapper.ObjectMapper.parseListObjects;
import static com.bruno.spring.Project.Spring.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = LoggerFactory.getLogger(PersonServices.class);


    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all people!");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person!");
        return parseObject(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoudException("No records found for this ID")), PersonDTO.class);
    }

    public PersonDTO createPerson(PersonDTO person) {
        logger.info("Creating one person!");
        return parseObject(repository.save(parseObject(person, Person.class)), PersonDTO.class);
    }

    public PersonDTO updatePerson(PersonDTO person) {
        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoudException("No records found for this ID"));
        entity.setName(person.getName());
        entity.setFistName(person.getFistName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());


        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void deletePerson(Long id) {
        logger.info("Deleting one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoudException("No records found for this ID"));
        repository.delete(entity);
    }
}
