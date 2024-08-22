package com.springsecuritytutorial.tutorial.mapper;

import com.springsecuritytutorial.tutorial.dto.BookDTO;
import com.springsecuritytutorial.tutorial.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO convertToDTO(Book book);
    Book convertToEntity(BookDTO bookDTO);
}
