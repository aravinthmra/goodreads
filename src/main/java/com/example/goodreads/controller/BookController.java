package com.example.goodreads.controller;

import com.example.goodreads.model.Author;
import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import com.example.goodreads.service.BookJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    public BookJpaService bookService;

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    public Book addbook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book) {
        return bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
    }

    @GetMapping("/books/{bookId}/publisher")
    public Publisher getPublisher(@PathVariable("bookId") int bookId) {
        return bookService.getBookPublisher(bookId);
    }

    @GetMapping("/books/{bookId}/authors")
    public List<Author> getBookAuthors(@PathVariable("bookId") int bookId) {
        return bookService.getBookAuthors(bookId);
    }
}
