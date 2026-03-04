package com.bruno.spring.Project.Spring.repository;

import com.bruno.spring.Project.Spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User,Long> {

    UserDetails findByUserName(String userName);
}
