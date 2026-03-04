package com.bruno.spring.Project.Spring.services;

import com.bruno.spring.Project.Spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails byUserName = userRepository.findByUserName(username);
        if (byUserName != null) return byUserName;
        else throw new UsernameNotFoundException("Username " + username + " not found");
    }
}
