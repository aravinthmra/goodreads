package com.example.goodreads.service;

import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookJpaService implements BookRepository {

    @Autowired
    private BookJpaRepository  bookJpaRepository;

    @Autowired
    private PublisherJpaService publisherJpaService;

    @Override
    public ArrayList<Book> getBooks() {
        List<Book> bookList = bookJpaRepository.findAll();
        ArrayList<Book> books = new ArrayList<>(bookList);
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        try {
            Book book = bookJpaRepository.findById(bookId).get();
            return book;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Book addBook(Book book) {
        Publisher newpublisher = book.getPublisher();
        int publisherId = newpublisher.getPublisherId();
        try {
            Publisher publisher = publisherJpaService.getPublisherById(publisherId);
            book.setPublisher(publisher);
            bookJpaRepository.save(book);
            return book;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong publisherId");
        }
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book newBook = getBookById(bookId);

        if(book.getName() != null) newBook.setName(book.getName());

        if(book.getImgUrl() != null) newBook.setImgUrl(book.getImgUrl());

        if(book.getPublisher() != null) {
            Publisher publisher = book.getPublisher();
            int publisherId = publisher.getPublisherId();
            Publisher newPublisher = publisherJpaService.getPublisherById(publisherId);
            newBook.setPublisher(newPublisher);
        }

        bookJpaRepository.save(newBook);
        return newBook;

    }

    @Override
    public void deleteBook(int bookId) {
        if(getBookById(bookId) != null){
            bookJpaRepository.deleteById(bookId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public Publisher getBookPublisher(int bookId){
        Book book = getBookById(bookId);
        return book.getPublisher();
    }
}
