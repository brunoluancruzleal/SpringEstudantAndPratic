package com.bruno.spring.Project.Spring.repository;

import com.bruno.spring.Project.Spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
