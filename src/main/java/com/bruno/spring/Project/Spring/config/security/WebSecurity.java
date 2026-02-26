package com.bruno.spring.Project.Spring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // 🛡️ Desabilita CSRF (comum em APIs Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 📑 Define como Stateless
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // 🔓 Login é público
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // 🔓 Registro é público
                        .requestMatchers(HttpMethod.POST, "/livros").hasRole("ADMIN") // 👨‍🏫 Só Admin cria livros
                        .anyRequest().authenticated() // 🔒 Todo o resto exige login
                ).build();
    }
}
