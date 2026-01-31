package com.bruno.spring.Project.Spring.services;


import com.bruno.spring.Project.Spring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all people!");
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            people.add(creatingMockPerson(i));
        }
        return people;
    }

    public Person findById(String id) {
        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setName("Bruno");
        person.setFistName("Luan");
        person.setLastName("Cruz");
        person.setAddress("Rua A, 123");
        return person;
    }

    private Person creatingMockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setName("Person name " + i);
        person.setFistName("First name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Address " + i);
        return person;
    }

    public Person createPerson(Person person) {
        logger.info("Creating one person!");
        return person;
    }

    public Person updatePerson(Person person) {
        logger.info("Updating one person!");
        return person;
    }

    public void deletePerson(String id) {
        logger.info("Deleting one person!");
    }
}
