package com.springsecuritytutorial.tutorial.controller;

import com.springsecuritytutorial.tutorial.dto.BookDTO;
import com.springsecuritytutorial.tutorial.model.Book;
import com.springsecuritytutorial.tutorial.security.jwt.JwtTokenUtil;
import com.springsecuritytutorial.tutorial.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;
    @PostMapping
    public Book createBook(@RequestBody BookDTO bookDTO, @RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        return bookService.createBook(bookDTO, token);
    }

    @GetMapping
    public List<BookDTO> getAllBooks(@RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        return bookService.getAllBooks(token);
    }
    @GetMapping("/{bookId}")
    public BookDTO getBookById(@PathVariable("bookId") Long bookId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        return bookService.getBookById(bookId, token);
    }
    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable("bookId") Long bookId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = JwtTokenUtil.extractToken(authorizationHeader);
        bookService.deleteBookById(bookId, token);
    }
   @PutMapping("/{bookId}")
   public Book updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookDTO bookDTO, @RequestHeader("Authorization") String authorizationHeader) {
       String token = JwtTokenUtil.extractToken(authorizationHeader);
        return bookService.updateBook(bookId, bookDTO, token);
   }

}
