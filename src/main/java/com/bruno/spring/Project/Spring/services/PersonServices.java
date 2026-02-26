package com.bruno.spring.Project.Spring.services;


import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.data.dto.PersonPatchDTO;
import com.bruno.spring.Project.Spring.exceptions.ResourceNotFoudException;
import com.bruno.spring.Project.Spring.mapper.BookMapper;
import com.bruno.spring.Project.Spring.model.Person;
import com.bruno.spring.Project.Spring.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

import static com.bruno.spring.Project.Spring.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = LoggerFactory.getLogger(PersonServices.class);


    @Autowired
    PersonRepository repository;

    @Autowired
    BookMapper bookMapper;

    public Page<PersonDTO> findAll(Pageable pageable) {
        logger.info("Finding all people!");

        Page<Person> all = repository.findAll(pageable);


        Page<PersonDTO> map = all.map(person -> {
            var dto = parseObject(person, PersonDTO.class);
            return dto;
        });

        return map;

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
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());


        return parseObject(repository.save(entity), PersonDTO.class);
    }

    @Transactional
    public PersonDTO parciaUpdatePerson(Long id, PersonPatchDTO dto) {

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoudException("No records found for this ID"));

        if (dto.name() != null) {
            person.setName(dto.name());
        }
        if (dto.firstName() != null) {
            person.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            person.setLastName(dto.lastName());
        }
        if (dto.address() != null) {
            person.setAddress(dto.address());
        }

        return bookMapper.toPersoDTO(person);

    }

    public void deletePerson(Long id) {
        logger.info("Deleting one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoudException("No records found for this ID"));
        repository.delete(entity);
    }
}
