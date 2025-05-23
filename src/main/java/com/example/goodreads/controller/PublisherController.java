package com.example.goodreads.controller;

import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import com.example.goodreads.service.BookJpaService;
import com.example.goodreads.service.PublisherJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PublisherController {

    @Autowired
    private PublisherJpaService publisherJpaService;

    @Autowired
    private BookJpaService bookService;


    @GetMapping("/publishers")
    public ArrayList<Publisher> getPublishers(){
        return publisherJpaService.getPublishers();
    }

    @GetMapping("/publishers/{publisherId}")
    public Publisher getPublisherById(@PathVariable Integer publisherId) {
        return publisherJpaService.getPublisherById(publisherId);
    }

    @PostMapping("/publishers")
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherJpaService.addPublisher(publisher);
    }

    @PutMapping("/publishers/{publisherId}")
    public Publisher updatePublisher(@RequestBody Publisher publisher, @PathVariable Integer publisherId) {
        return publisherJpaService.updatePublisher(publisherId, publisher);
    }

    @DeleteMapping("/publishers/{publisherId}")
    public void deletePublisher(@PathVariable Integer publisherId) {
        publisherJpaService.deletePublisher(publisherId);
    }

    @PostMapping("/publishers/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

}
