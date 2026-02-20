package com.bruno.spring.Project.Spring.controller;

import com.bruno.spring.Project.Spring.controller.doc.BookControllerDoc;
import com.bruno.spring.Project.Spring.data.dto.BookDTO;
import com.bruno.spring.Project.Spring.services.BooksServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/books")
public class BookController implements BookControllerDoc {

    private final BooksServices services;


    public BookController(BooksServices services) {
        this.services = services;
    }

    @GetMapping("/all")
    @Override
    public List<BookDTO> findAll() {
        var find = services.findAll();
        for (BookDTO bookDTO : find) {
            hateos(bookDTO);
        }

        return find;
    }

    @GetMapping("/{id}")
    @Override
    public BookDTO findById(@PathVariable Long id) {
        BookDTO bookDTO = services.findById(id);
        hateos(bookDTO);
        return bookDTO;
    }

    @PostMapping()
    @Override
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        var creatingBook = services.createBook(bookDTO);
        hateos(creatingBook);
        return creatingBook;
    }

    @PutMapping
    @Override
    public BookDTO updateBook(@RequestBody BookDTO bookDTO) {
        var update = services.updateBook(bookDTO);
        hateos(update);
        return update;

    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteBook(@PathVariable Long id) {
         services.deleteBook(id);
    }


    private static void hateos(BookDTO bookDTO) {
        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getId())).withSelfRel());
        bookDTO.add(linkTo(methodOn(BookController.class).findAll()).withRel("All-books"));
        bookDTO.add(linkTo(methodOn(BookController.class).createBook(bookDTO)).withRel("create-book"));
        bookDTO.add(linkTo(methodOn(BookController.class).updateBook(bookDTO)).withRel("update-book"));
    }
}
