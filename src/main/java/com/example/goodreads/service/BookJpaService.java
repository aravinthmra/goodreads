package com.example.goodreads.service;

import com.example.goodreads.model.Author;
import com.example.goodreads.model.Book;
import com.example.goodreads.model.Publisher;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookJpaService implements BookRepository {

    @Autowired private EntityManager entityManager;

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
    @Transactional
    public Book updateBook(int bookId, Book book) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Book> update = cb.createCriteriaUpdate(Book.class);
        Root<Book> root = update.from(Book.class);

        update.set("name", book.getName());
        update.set("imgUrl", book.getImgUrl());

        update.where(cb.equal(root.get("id"), bookId));

        entityManager.createQuery(update).executeUpdate();

        return book;

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

    @Override
    public List<Book> getAllBooks(List<Integer> bookIds) {
        return bookJpaRepository.findAllById(bookIds);
    }
}
