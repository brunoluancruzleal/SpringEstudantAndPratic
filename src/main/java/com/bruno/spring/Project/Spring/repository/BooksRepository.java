package com.bruno.spring.Project.Spring.repository;

import com.bruno.spring.Project.Spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {
}
