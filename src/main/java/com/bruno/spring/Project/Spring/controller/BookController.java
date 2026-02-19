package com.bruno.spring.Project.Spring.controller;

import com.bruno.spring.Project.Spring.model.Book;
import com.bruno.spring.Project.Spring.services.BooksServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BooksServices services;


    public BookController(BooksServices services) {
        this.services = services;
    }



}
