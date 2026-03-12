package com.bruno.spring.Project.Spring.services;

import com.bruno.spring.Project.Spring.data.dto.security.AccountCredentiasDTO;
import com.bruno.spring.Project.Spring.data.dto.security.TokenDTO;
import com.bruno.spring.Project.Spring.model.User;
import com.bruno.spring.Project.Spring.repository.UserRepository;
import com.bruno.spring.Project.Spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private UserRepository repository;


    public ResponseEntity<TokenDTO> signIn(AccountCredentiasDTO credentias){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentias.userName(),
                        credentias.passWord()
                )
        );


        var user = repository.findByUserName(credentias.userName());
        if (user == null) throw new UsernameNotFoundException("UserName"+ credentias.userName() +"Not foud");


        var token = provider.createToken(
                credentias.userName(),
                User.getRoles()
        );

        return ResponseEntity.ok(token);
    }

}
