package com.springsecuritytutorial.tutorial.repository;

import com.springsecuritytutorial.tutorial.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
}
