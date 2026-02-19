package com.bruno.spring.Project.Spring.services;

import com.bruno.spring.Project.Spring.data.dto.BookDTO;
import com.bruno.spring.Project.Spring.exceptions.ResourceNotFoudException;
import com.bruno.spring.Project.Spring.mapper.BookMapper;
import com.bruno.spring.Project.Spring.model.Book;
import com.bruno.spring.Project.Spring.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServices {
    private final BooksRepository repository;
    @Autowired
    private BookMapper mapper;

    public BooksServices(BooksRepository repository) {
        this.repository = repository;
    }

    public List<BookDTO> findAll() {
        List<Book> book = repository.findAll();
        return mapper.toListDTO(book);
    }

    public BookDTO findById(Long id) {
        Book entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoudException("Book not found")
        );
        return mapper.toDTO(entity);
    }
}
