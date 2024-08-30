package com.springsecuritytutorial.tutorial.mapper;

import com.springsecuritytutorial.tutorial.dto.BookDTO;
import com.springsecuritytutorial.tutorial.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }
}
