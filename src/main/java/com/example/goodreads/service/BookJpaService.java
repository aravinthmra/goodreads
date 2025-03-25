package com.example.goodreads.service;

import com.example.goodreads.model.Author;
import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookJpaService implements BookRepository {

    @Autowired
    private BookJpaRepository  bookJpaRepository;

    @Autowired
    private PublisherJpaService publisherJpaService;

    @Autowired
    private AuthorJpaService authorJpaService;

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
        int publisherId = book.getPublisher().getPublisherId();

        Publisher publisher = publisherJpaService.getPublisherById(publisherId);
        book.setPublisher(publisher);

        List<Integer> authorIds = book.getAuthors().stream()
                .map(Author::getAuthorId)
                .collect(Collectors.toList());

        List<Author> authors = authorJpaService.getAllAuthors(authorIds);
        if(authorIds.size() != authors.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of authors are not found");

        book.setAuthors(authors);

        return bookJpaRepository.save(book);
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

//        book cannot exist if any of it's Author doesn't exist
        if(book.getAuthors() != null) {
            List<Integer> authorIds = book.getAuthors().stream()
                    .map(Author::getAuthorId)
                    .collect(Collectors.toList());

            List<Author> authors = authorJpaService.getAllAuthors(authorIds);
            if(authorIds.size() != authors.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of authors are not found");
            newBook.setAuthors(authors);
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

    @Override
    public List<Author> getBookAuthors(int bookId) {
        Book book = getBookById(bookId);
        return book.getAuthors();
    }
}
