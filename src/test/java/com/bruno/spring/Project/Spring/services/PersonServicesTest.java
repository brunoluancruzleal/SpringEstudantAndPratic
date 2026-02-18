package com.bruno.spring.Project.Spring.services;

import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.model.Person;
import com.bruno.spring.Project.Spring.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.bruno.spring.Project.Spring.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.util.FunctionUtils.where;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices personServices;


    @Test
    @DisplayName("Finding one person!")
    void findById() {
        PersonDTO personDTO = new PersonDTO(1L,"Bruno","Bruno");


        when(repository.findById(1L)).thenReturn(Optional.of(parseObject(personDTO, Person.class)));

        Optional<PersonDTO> foundPerson = Optional.ofNullable(personServices.findById(1L));

        assertTrue(foundPerson.isPresent());
    }
}