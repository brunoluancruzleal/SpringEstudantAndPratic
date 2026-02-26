package com.bruno.spring.Project.Spring.mapper;

import com.bruno.spring.Project.Spring.data.dto.BookDTO;
import com.bruno.spring.Project.Spring.data.dto.PersonDTO;
import com.bruno.spring.Project.Spring.data.dto.PersonPatchDTO;
import com.bruno.spring.Project.Spring.model.Book;
import com.bruno.spring.Project.Spring.model.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    // Transforma um BooksDTO em um Book
    Book toEntity(BookDTO bookDTO);

    // Transforma um Book em um BooksDTO
    BookDTO toDTO(Book book);
    PersonDTO toPersoDTO(Person person);

    List<BookDTO> toListDTO(List<Book> book);



}
