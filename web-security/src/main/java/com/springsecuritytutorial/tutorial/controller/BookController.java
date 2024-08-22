package com.springsecuritytutorial.tutorial.controller;

import com.springsecuritytutorial.tutorial.dto.BookDTO;
import com.springsecuritytutorial.tutorial.security.jwt.JwtTokenUtil;
import com.springsecuritytutorial.tutorial.service.BookService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {


    private final ObjectProvider<BookService> bookServiceProvider;

    public BookController(ObjectProvider<BookService> bookServiceProvider) {
        this.bookServiceProvider = bookServiceProvider;
    }
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO, @RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        BookService bookService = bookServiceProvider.getObject();
        return bookService.createBook(bookDTO, token);
    }

    @GetMapping
    public List<BookDTO> getAllBooks(@RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        BookService bookService = bookServiceProvider.getObject();
        return bookService.getAllBooks(token);
    }
    @GetMapping("/{bookId}")
    public BookDTO getBookById(@PathVariable("bookId") Long bookId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        BookService bookService = bookServiceProvider.getObject();
        return bookService.getBookById(bookId, token);
    }
    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable("bookId") Long bookId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        BookService bookService = bookServiceProvider.getObject();
        bookService.deleteBookById(bookId, token);
    }
   @PutMapping("/{bookId}")
   public BookDTO updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookDTO bookDTO, @RequestHeader("Authorization") String authorizationHeader) {
       String token = JwtTokenUtil.extractToken(authorizationHeader);
        BookService bookService = bookServiceProvider.getObject();
        return bookService.updateBook(bookId, bookDTO, token);
   }

}
