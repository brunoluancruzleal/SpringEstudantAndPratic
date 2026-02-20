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

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = mapper.toEntity(bookDTO);
        Book savedBook = repository.save(book);
        return mapper.toDTO(savedBook);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        Book entity = repository.findById(bookDTO.getId()).orElseThrow(
                () -> new ResourceNotFoudException("Book not found"));

        entity.setAuthor(bookDTO.getAuthor());
        entity.setTitle(bookDTO.getTitle());
        entity.setPrice(bookDTO.getPrice());

        Book save = repository.save(entity);
        return mapper.toDTO(save);
    }

    public void deleteBook(Long id){
        Book entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoudException("Book not found"));
        repository.delete(entity);
    }
}
