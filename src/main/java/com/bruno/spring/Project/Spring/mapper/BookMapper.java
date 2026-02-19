package com.bruno.spring.Project.Spring.mapper;

import com.bruno.spring.Project.Spring.data.dto.BookDTO;
import com.bruno.spring.Project.Spring.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    // Transforma um BooksDTO em um Book
    Book toDomain(BookDTO bookDTO);

    // Transforma um Book em um BooksDTO
    BookDTO toDTO(Book book);

    List<BookDTO> toListDTO(List<Book> book);
}
