package com.springsecuritytutorial.tutorial.service;

import com.springsecuritytutorial.tutorial.model.AppUser;
import com.springsecuritytutorial.tutorial.dto.BookDTO;
import com.springsecuritytutorial.tutorial.mapper.BookMapper;
import com.springsecuritytutorial.tutorial.model.Book;
import com.springsecuritytutorial.tutorial.repository.BookRepository;
import com.springsecuritytutorial.tutorial.repository.UserRepository;
import com.springsecuritytutorial.tutorial.security.jwt.JwtService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, BookMapper bookMapper, JwtService jwtService, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    public BookDTO createBook(BookDTO bookDTO, String token) {
        Long userId = jwtService.extractUserId(token);
        AppUser appUser = userRepository.findById(userId).orElseThrow();

        Book book = bookMapper.convertToEntity(bookDTO);
        book.setUser(appUser);
        book = bookRepository.save(book);

        return bookMapper.convertToDTO(book);
    }

    public BookDTO getBookById(Long bookId, String token) {
        Long userId = jwtService.extractUserId(token);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getUser().getId().equals(userId)) {
            throw new RuntimeException("You do not have permission to view this book");
        }
        return bookMapper.convertToDTO(book);
    }

    public List<BookDTO> getAllBooks(String token) {
        Long userId = jwtService.extractUserId(token);
        List<Book> books = bookRepository.findByUserId(userId);
        return books.stream()
                .map(bookMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteBookById(Long bookId, String token) {
        Long userId = jwtService.extractUserId(token);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getUser().getId().equals(userId)) {
            throw new RuntimeException("You do not have permission to delete this book");
        }

        bookRepository.deleteById(bookId);
    }

    public BookDTO updateBook(Long bookId, BookDTO bookDTO, String token) {
        Long userId = jwtService.extractUserId(token);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getUser().getId().equals(userId)) {
            throw new RuntimeException("You do not have permission to update this book");
        }
        book.setTitle(bookDTO.getTitle());
        Book updatedBook = bookRepository.save(book);
        return bookMapper.convertToDTO(updatedBook);
    }
}
